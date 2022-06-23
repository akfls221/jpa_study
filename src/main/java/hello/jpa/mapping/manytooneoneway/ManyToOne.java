package hello.jpa.mapping.manytooneoneway;

import javax.persistence.*;

/**
 * N:1 단방향
 * 다대일 단방향 관계
 * Member Entity에서 FK를 관리
 */
public class ManyToOne {

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("practice");
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        Member member = new Member("user1");
        Member member2 = new Member("user2");
        Team team = new Team("team1");

        tx.begin();
        em.persist(member);
        em.persist(member2);
        em.persist(team);

        member.setTeam(team);
        member2.setTeam(team);


        tx.commit();

    }
}
