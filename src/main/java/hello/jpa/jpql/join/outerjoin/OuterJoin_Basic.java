package hello.jpa.jpql.join.outerjoin;

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
public class OuterJoin_Basic {

    private final static Logger logger = LoggerFactory.getLogger(OuterJoin_Basic.class);

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

            //Oter JPQL
            List<Object[]> result = em.createQuery("select m, t from Member m left join m.team t")
                    .getResultList();

            for (Object[] objects : result) {
                Member findMember = (Member) objects[0];
                Team findTeam = (Team) objects[1];
                logger.info("find Member : {} find Team : {}", findMember, findTeam);
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
