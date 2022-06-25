package hello.jpa.mapping.onetoonetwoway;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * 1:1 단방향
 * 일대일 단방향
 * Member Entity에서 FK를 관리
 */
public class OneToOne {

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("practice");
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        Member member = new Member("taekwon");
        Locker locker = new Locker("taekwon_Locker");
        member.setLocker(locker);

        em.persist(locker);
        em.persist(member);

        System.out.println("username = " + locker.getMember().getUsername());


        tx.commit();

    }
}
