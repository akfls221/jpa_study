package hello.jpa.jpql.join.fetchjoin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

/**
 * InnerJoin
 */
public class FetchJoin_Basic {

    private final static Logger logger = LoggerFactory.getLogger(FetchJoin_Basic.class);

    public static void main(String[] args){

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("practice");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            //Team 생성
            Team team = new Team("teamA");
            em.persist(team);

            //Member 생성
            Member member = new Member("엄태권");
            member.setTeam(team);
            em.persist(member);
            em.flush();
            em.clear();

            //InnerJoin JPQL
            List<Member> resultList = em.createQuery("select m from Member m join fetch m.team", Member.class)
                    .getResultList();

            for (Member findMember : resultList) {
                logger.info("this is findMember and Team join fetch {}, {}", findMember.getName(), findMember.getTeam().getTeamName());
            }




            tx.commit();
        } catch (Exception e) {
            logger.error("error : ", e);
            tx.rollback();
        } finally {
            em.close();
        }

    }
}
