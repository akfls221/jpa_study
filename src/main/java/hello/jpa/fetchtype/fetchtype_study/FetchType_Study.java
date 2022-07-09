package hello.jpa.fetchtype.fetchtype_study;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

/**
 * Member - Team : fetch.EAGER 로 즉시 조회(N:1)
 * Member - Order : fetch.Lazy 로 지연 조회(1:N)
 */
public class FetchType_Study {

    private final static Logger logger = LoggerFactory.getLogger(FetchType_Study.class);

    public static void main(String[] args){

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("practice");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            //Team 등록
            Team team = new Team("2", "TEAM1");
            em.persist(team);

            //Order 등록
            Order order = new Order("3");
            order.setOrderCount(10);
            em.persist(order);

            //Member 등록
            Member member = new Member("1", "엄태권");
            member.setTeam(team);
            member.addOrder(order);
            em.persist(member);


            em.flush();
            em.clear(); //DB에 데이터 저장후 clear

            //외부조인으로 Team과 함께 가져온 후 Team을 반환
            Member findMember = em.find(Member.class, "1");
            logger.info("this is teamName(EAGER) : {}",findMember.getTeam().getName());

            //Lazy로 조회시 가져오게 됨.
            List<Order> orderList = findMember.getOrderList();
            for (Order result : orderList) {
                logger.info("this is Lazy order : {}", result.getOrderCount());
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
