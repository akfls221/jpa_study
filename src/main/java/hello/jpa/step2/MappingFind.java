package hello.jpa.step2;

import hello.jpa.step1.Detach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class MappingFind {
    private final static Logger logger = LoggerFactory.getLogger(MappingFind.class);

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("practice");
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            /**
             * 객체 그래프 탐색
             * 객체를 통해 연관된 엔티티를 조회함.
             */

            TestTeam team = new TestTeam("teamA");
            em.persist(team);

            TestMember member = new TestMember("member1");
            member.setTeam(team);
            em.persist(member);

            logger.info("객체 그래프 탐색 : " + member.getTeam().getTeamName());



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
