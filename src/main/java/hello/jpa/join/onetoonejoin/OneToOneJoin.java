package hello.jpa.join.onetoonejoin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *  일대일 조인테이블
 *  조인 테이블의 가장 큰 단점은 테이블을 하나 추가해야 한다는 점이며, 관리해야 하는 테이블이 늘어나고
 *  조인테이블 까지 함께 조인해야 한다.
 *  @@@따라서 기본은 조인 컬럼을 사용하고 필요하다고 판단되면 조인 테이블을 사용하자
 */
public class OneToOneJoin {

    private final static Logger logger = LoggerFactory.getLogger(OneToOneJoin.class);

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("practice");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        // 식별자 클래스 ParentId를 직접 생성하여 사용함.
        Parent parent = new Parent("부모입니다.");
        em.persist(parent);
        Child child = new Child("자식입니다.");
        em.persist(child);


        parent.setChild(child);



        tx.commit();
    }
}
