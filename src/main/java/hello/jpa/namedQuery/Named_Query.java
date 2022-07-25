package hello.jpa.namedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * NamedQuery
 * 동적 쿼리 : em.createQuery 처럼 JPQL을 문자로 완성해서 직접 넘기는 것
 * 정적 쿼리 :미리 정의한 쿼리에 이름을 부여해서 필요할 때 사용할 수 있음.(한 번 정의하면 변경할 수 없음)
 * @NamedQuery 어노테이션을 통해 작성하며, 여러개의 Named 쿼리를 정의하려면 @NamedQueries 를 사용하면 됨.
 **/
public class Named_Query {

    private final static Logger logger = LoggerFactory.getLogger(Named_Query.class);

    public static void main(String[] args){

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("practice");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            Member member = new Member("엄태권", 29);
            Team team = new Team("teamA", "owen");
            em.persist(team);
            em.persist(member);

            String namedQuery = em.createNamedQuery("Member.selectByName", String.class)
                    .setParameter("userName", "엄태권")
                    .getSingleResult();

            logger.info("this is namedQuery just one : {}", namedQuery);

            Team findByLeaderName = em.createNamedQuery("Team.findByLeader", Team.class)
                    .setParameter("leaderName", "owen")
                    .getSingleResult();

            logger.info("this is @NamedQueries : {}", findByLeaderName);

            tx.commit();
        } catch (Exception e) {
            logger.error("error : ", e);
            tx.rollback();
        } finally {
            em.close();
        }

    }
}
