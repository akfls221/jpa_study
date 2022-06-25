package hello.jpa.mapping.manytooneoneway;

import javax.persistence.*;

/**
 * N:1 단방향
 * 다대일 단방향 관계
 * Member Entity에서 FK를 관리
 * @@ em.persist 순서에 따라 Update 쿼리문이 나갈 위험이 있음.
 * @@ 즉 member을 먼저 persist 한경우 insert query문이 나가고, team은 영속성에 등록이 안되어 있기 때문에
 *    이후 Team 이 영속성 컨텍스트에 등록되면서 update 문이 또 나감
 *    즉 먼저 team을 영속성에 등록한 후 member을 등록해야 영속성 관계로 인해 insert문 하나로 연관관계 까지 끝낼 수 있음.
 */
public class ManyToOne {

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("practice");
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Team team = new Team("team1");
        Member member = new Member("user1");
        Member member2 = new Member("user2");
        member.setTeam(team);
        member2.setTeam(team);

        em.persist(team);
        em.persist(member);
        em.persist(member2);

        tx.commit();

    }
}
