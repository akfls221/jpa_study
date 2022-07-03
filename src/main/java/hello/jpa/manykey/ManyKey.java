package hello.jpa.manykey;

import hello.jpa.mapping.manytomanybestway.ManyToMany;
import hello.jpa.step1.PersistenceTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *  복합키 : 비식별 관계 매핑
 * @IdClass를 사용하여 별도의 식별자 테이블을 생성(데이터베이스 지향적)
 * 참고로 조인할 상대 컬럼(Parent 쪽 1, 2 번 ID)의 명칭이 같으면 referencedColumnName 은 생략 가능
 */
public class ManyKey {

    private final static Logger logger = LoggerFactory.getLogger(ManyKey.class);

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("practice");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        Parent parent = new Parent("엄태권");
        em.persist(parent);

        Child child = new Child();
        child.setParent(parent);
        em.persist(child);

        tx.commit();


        ParentId parentId = new ParentId(1L, 2L);
        Parent findResult = em.find(Parent.class, parentId);
        logger.info("find Result : {}", findResult);
    }
}
