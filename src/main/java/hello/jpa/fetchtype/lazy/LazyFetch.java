package hello.jpa.fetchtype.lazy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * fetchType Lazy
 * - Team 객체에 대해 프록시객체로 받아옴.
 * - 실제 해당 프록시 객체를 사용해야할 타임에 SQL 쿼리문을 날림.
 */
public class LazyFetch {

    private final static Logger logger = LoggerFactory.getLogger(LazyFetch.class);

    public static void main(String[] args){

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("practice");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            //Team 등록
            Team team = new Team("2", "TEAM1");
            em.persist(team);

            //Member 등록
            Member member = new Member("1", "엄태권");
            member.setTeam(team);
            em.persist(member);

            em.flush();
            em.clear(); //DB에 데이터저장 후 clear

            //em.find 이후 .getTeam().getName() 부분에서 프록시 객체에 있는 값을 실제 SQL 조회를 통해 가져옴.
            // findMember 조회를 위한 select, getTeam().getName()을 위한 select 한번
            Member findMember = em.find(Member.class, "1");
            logger.info("this is teamName : {}",findMember.getTeam().getName());


            tx.commit();
        } catch (Exception e) {
            logger.error("error : ", e);
            tx.rollback();
        } finally {
            em.close();
        }

    }
}
