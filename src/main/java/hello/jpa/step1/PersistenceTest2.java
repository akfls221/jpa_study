package hello.jpa.step1;

import hello.jpa.entity.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class PersistenceTest2 {

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("practice");
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
        tx.begin();
            Member member = em.find(Member.class, 1L);//영속성 컨텍스트의 1차캐시에 없기때문에 쿼리문으로 조회

            member.setUsername("Hello JPA"); //1차캐시에 있는 대상을 변경(변경감지) -> 쿼리문 나감

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
