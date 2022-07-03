package hello.jpa.manykey.manykeyforembeddedid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *  복합키 : 비식별 관계 매핑
 * @EmbeddedId 사용하여 별도의 식별자 테이블을 생성(객체지향 적)
 * 사실 @IdClass 든 @EmbeddedId든 복합 키에는 @GenerateValue를 사용할 수 없음.(직접 지정만 가능)
 * @IdClass와는 다르게 @EmbeddedId를 적용한 식별자 클래스는 식별자 클래스에 기본 키를 직접 매핑함.(Parent.class 참조)
 */
public class ManyKeyEmbeddedId {

    private final static Logger logger = LoggerFactory.getLogger(ManyKeyEmbeddedId.class);

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("practice");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        // 식별자 클래스 ParentId를 직접 생성하여 사용함.
        ParentId parentId = new ParentId(1L, 2L);
        Parent parent = new Parent();
        parent.setId(parentId);
        parent.setName("엄태권111");
        em.persist(parent);

        tx.commit();

        Parent findResult = em.find(Parent.class, parentId);
        logger.info("find Result : {}", findResult);
    }
}
