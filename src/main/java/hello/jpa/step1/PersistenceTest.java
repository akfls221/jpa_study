package hello.jpa.step1;

import hello.jpa.entity.Member;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.logging.Logger;

public class PersistenceTest {

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("practice");
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        Member member1 = new Member(1L, "taekwon", 29);
        try {
        tx.begin();
        em.persist(member1); //영속 상태

        em.find(Member.class, 1L); //쿼리 안나감.(영속성 컨텍스트에서 1차캐시로 받아옴)

        tx.commit();

        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        factory.close();

    }
}
