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
            Team teamA = new Team("teamA");
            em.persist(teamA);

            Team teamB = new Team("teamB");
            em.persist(teamB);

            //Member 생성
            Member member1 = new Member("엄태권1");
            member1.setTeam(teamA);
            em.persist(member1);

            Member member2 = new Member("엄태권2");
            member2.setTeam(teamB);
            em.persist(member2);
            em.flush();
            em.clear();

            //join fetch basic
            List<Member> resultList = em.createQuery("select m from Member m join fetch m.team", Member.class)
                    .getResultList();

            for (Member findMember : resultList) {
                logger.info("this is findMember and Team join fetch {}, {}", findMember.getName(), findMember.getTeam().getTeamName());
            }

            //collection join fetch
            /**
             * @참고 : toString 순환참조 발생 조심(양방향의 경우 서로 객체를 참조하고 있어 객체 출력시 toString이 호출되는데 이때 무한루프에 빠짐
             *       즉 하나의 to String을 제거하거나, @ToString(exclude = "상대방의 Entity") 를통해 출력하는게 좋음.
             */
            List<Team> resultTeam = em.createQuery("select t from Team t join fetch t.member where t.teamName = 'teamA'", Team.class)
                    .getResultList();

            for (Team team : resultTeam) {
                logger.info("this is one to many fetch join(collection join fetch) team : {}", team);

                for (Member listMember : team.getMember()) {
                    logger.info("this is one to many fetch join(Collection join fetch) member : {}", listMember);
                }
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
