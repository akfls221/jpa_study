package hello.jpa.step2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class MappingBidirectionalSave {
    private final static Logger logger = LoggerFactory.getLogger(MappingBidirectionalSave.class);

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("practice");
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            TestTeam team = new TestTeam("teamA");
            em.persist(team);

            TestMember member1 = new TestMember("member1");
            member1.setTeam(team); // 연관관계 편의 메서드에 따라 양방향 설정 적용
            em.persist(member1);

            TestMember member2 = new TestMember("member2");
            member2.setTeam(team); // 연관관계 편의 메서드에 따라 양방향 설정 적용
            em.persist(member2);

            List<TestMember> members = team.getMembers();
            for (TestMember member : members) {
                logger.info("양방향 연관관계 적용(연관관계 편의 메서드) : " + member.getUsername());
            }


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
