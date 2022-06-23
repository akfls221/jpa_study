package hello.jpa.mapping.onetomanyoneway;

import javax.persistence.*;

/**
 * 1:N 단방향
 * 일대다 단방향
 * Member Entity에서 FK를 관리
 * 그러나 Team에서 연관관계를 맺기 때문에 다른 테이블에 있는 FK를 참조
 * 단점 : 본인 테이블에 있을 경우 INSERT SQL 하나로 처리가 가능하지만, UPDATE 문을 한번 더 해야 함.
 * @@@다행히 JPA2.0부터 일대다 단방향 관계에서 대상 테이블에 외래 키가 있는 매핑을 허용함.
 */
public class OneToMany {

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

        team.getMembers().add(member); //Member Table에 TeamID UpdateQuery문 실행
        team.getMembers().add(member2); //Member Table에 TeamID UpdateQuery문 실행
        em.persist(team);

        tx.commit();

    }
}
