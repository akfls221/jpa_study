package hello.jpa.step2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class MappingUpdate {
    private final static Logger logger = LoggerFactory.getLogger(MappingUpdate.class);

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("practice");
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            TestTeam team = new TestTeam("teamA");
            em.persist(team);

            TestTeam team2 = new TestTeam("teamB");
            em.persist(team2);

            TestMember member = new TestMember("member1");
            member.setTeam(team);
            em.persist(member);
            logger.info("before Update = " + member.getTeam().getTeamName());

            TestMember findMember = em.find(TestMember.class, 3L);
            findMember.setTeam(team2);
            em.flush();
            tx.commit();
            logger.info("after Update = " + findMember.getTeam().getTeamName());

        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        factory.close();

    }
}
