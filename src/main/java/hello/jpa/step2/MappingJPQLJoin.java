package hello.jpa.step2;

import hello.jpa.entity.Member;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class MappingJPQLJoin {
    private final static Logger logger = LoggerFactory.getLogger(MappingJPQLJoin.class);

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("practice");
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            /**
             * JPQL 단순 조인
             */

            TestTeam team = new TestTeam("teamA");
            em.persist(team);

            TestMember member1 = new TestMember("member1");
            member1.setTeam(team);
            em.persist(member1);

            TestMember member2 = new TestMember("member2");
            member2.setTeam(team);
            em.persist(member2);
            tx.commit();

            String jpql = "select m from TestMember m join m.team t where t.teamName=:teamName";

            List<TestMember> resultList = em.createQuery(jpql, TestMember.class)
                    .setParameter("teamName", "teamA")
                    .getResultList();

            for (TestMember member : resultList) {
                logger.info("jpql join 조회 : "+ member.getUsername());
            }


        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        factory.close();

    }
}
