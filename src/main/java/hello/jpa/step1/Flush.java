package hello.jpa.step1;

import hello.jpa.entity.Member;

import javax.persistence.*;
import java.util.List;

public class Flush {
    /**
     * JPQL 실행시 자동 Flush() 진행.
     */

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("practice");
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        Member member1 = new Member(2L, "taekwon", 29);

        tx.begin();
        em.persist(member1); // 영속 단계

        Query query = em.createQuery("select m from Member m", Member.class); // 자동 Flush
        List<Member> result = query.getResultList();

    }
}
