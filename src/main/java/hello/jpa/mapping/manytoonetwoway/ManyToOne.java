package hello.jpa.mapping.manytoonetwoway;

import javax.persistence.*;
import java.util.List;

/**
 * N:1 양방향
 * 다대일 양방향 관계
 * Member Entity에서 FK를 관리
 * Team에서도 Member 객체를 참조함.
 */
public class ManyToOne {

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("practice");
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        Member member = new Member("user1");
        Member member2 = new Member("user2");
        Team team = new Team("team1");
        member.setTeam(team);
        member2.setTeam(team);

        tx.begin();
        em.persist(team);
        em.persist(member);
        em.persist(member2);


        tx.commit();

        Team selectTeam = em.createQuery("select t from Team t", Team.class).getSingleResult();
        List<Member> members = selectTeam.getMembers();
        for (Member member1 : members) {
            System.out.println("member List = " + member1.getUsername());
        }

    }
}
