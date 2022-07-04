package hello.jpa.join.ontetomanyoneway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *  일대다 조인테이블
 */
public class OneToManyJoin {

    private final static Logger logger = LoggerFactory.getLogger(OneToManyJoin.class);

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


        parent.getChildList().add(child);



        tx.commit();
    }
}
