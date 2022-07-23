package hello.jpa.pathexpression;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

/**
 * InnerJoin
 * [주의사항]
 * 항상 내부조인이다(묵시적 조인)
 * 컬렉션은 경로 탐색의 끝이다(더이상 조회 불가) / 컬렉션에서 경로탐색을 하려면 명시적으로 조인을 해서 별칭을 얻으면 된다.
 * 경로 탐색은 주로 SELECT, WHERE 절에서 사용하지만 묵시적 조인으로 인해 SQL의 FROM 절에 영향을 준다.
 * @@성능이 중요하면 분석하기 쉽도록 묵시적 조인보다는 명시적 조인을 사용하는것이 좋음.
 */
public class Path_Expression {

    private final static Logger logger = LoggerFactory.getLogger(Path_Expression.class);

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

            //Order 생성
            Order order1 = new Order();
            order1.setProduct("생산품1");
            em.persist(order1);

            Order order2 = new Order();
            order1.setProduct("생산품2");
            em.persist(order2);

            //Member 생성
            Member member1 = new Member("엄태권1", 11);
            member1.setTeam(teamA);
            member1.addOrder(order1);
            em.persist(member1);

            Member member2 = new Member("엄태권2", 29);
            member2.setTeam(teamB);
            member2.addOrder(order2);
            em.persist(member2);
            em.flush();
            em.clear();


            /**
             * 상태 필드 경로 탐색
             */
            List resultList = em.createQuery("select m.name, m.age from Member m")
                    .getResultList();

            for (Object o : resultList) {
                logger.info("this is 상태 필드 경로 탐색 : {}", o);
            }

            /**
             * 단일 값 연관 경로 탐색
             */
            List<Member> members = em.createQuery("select o.member from Order o", Member.class)
                    .getResultList();

            for (Member member : members) {
                logger.info("this is 단일 값 연관 경로 탐색 : {}", member.getName());
            }

            /**
             * 컬렉션 값 연관 경로 탐색
             */
            List<Member> resultMember = em.createQuery("select o.member from Order o", Member.class)
                    .getResultList();

            for (Member member : resultMember) {
                logger.info("컬렉션 값 연관 경로 탐색 : {}", member);
            }

            //실패 Query (컬렉션에선 경로탐색 불가능)
            //em.createQuery("select o.member.name from Order o", Member.class)
            //        .getResultList();

            //위처럼 컬렉션에서 경로 탐색을 하려면
            List<String> resultMemberName = em.createQuery("select m.name from Order o join o.member m", String.class)
                    .getResultList();

            for (String member : resultMemberName) {
                logger.info("컬렉션에서 경로 탐색하기 : {}", member);
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
