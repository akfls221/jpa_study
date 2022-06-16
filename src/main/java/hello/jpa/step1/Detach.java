package hello.jpa.step1;

import hello.jpa.entity.Member;

import javax.persistence.*;
import java.util.List;

public class Detach {
    /**
     * 영속 상태였다가 영속성 컨텍스트가 더는 관리하지 않는 상태
     */

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("practice");
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        Member member1 = new Member(2L, "taekwon", 29);

        tx.begin();
        em.persist(member1); // 영속 단계

        em.detach(member1); //준영속(특정 엔티티만 준영속 상태 -> Member.class)

        Query query = em.createQuery("select m from Member m where m.id = 2L", Member.class); //준영속 상태이기 때문에 조회 불가 및 persist 쿼리 안나감.
        query.getResultList();

    }
}
