package hello.jpa.proxy;

import hello.jpa.join.ontetomanyoneway.Child;
import hello.jpa.join.ontetomanyoneway.Parent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * 프록시
 * - JPA의 지연로딩을 사용하려는 경우 실제 엔티티 객체 대신 DB를 조회를 지연할 수 있는 가짜 객체가 필요하며 이를 프록시 객체라 함.
 *  - EntityManager.getReference() 메소드를 통해 엔티티를 실제 사용하는 시점까지 데이터베이스 조회를 미룰 수 있음.
 *  - member.getName() 처럼 실제 사용될 때 데이터베이스를 조회해 실제 엔티티 객체를 생성하는데 이를 프록시 객체의 초기화라 함.
 *  - 준영속 상태의 프록시를 초기화하면 문제가 발생함.(LazyInitializationException)
 */
public class Proxy {

    private final static Logger logger = LoggerFactory.getLogger(Proxy.class);

    public static void main(String[] args){

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("practice");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            Member member = new Member("1", "member1");
            Member member2 = new Member("2", "member2");
            em.persist(member);
            em.persist(member2);

            em.flush();
            em.clear(); //DB에 데이터만 저장하고 영속성 초기화

            Member reference = em.getReference(Member.class, "1");
            Member reference2 = em.getReference(Member.class, "2");
            logger.info("this is proxy 초기화 되었나요 : {}", emf.getPersistenceUnitUtil().isLoaded(reference));
            reference.getName(); //쿼리 조회
            logger.info("this is proxy 초기화 되었나요 : {}", emf.getPersistenceUnitUtil().isLoaded(reference));

            /**
             * 준영속 상태의 오류
             * LazyInitializationException 발생
             */
            //em.detach(reference2);
            em.clear();
            logger.info("this is proxy 초기화 되었나요 : {}", emf.getPersistenceUnitUtil().isLoaded(reference2));
            reference2.getName();
            logger.info("this is proxy 초기화 되었나요 : {}", emf.getPersistenceUnitUtil().isLoaded(reference2));

            tx.commit();
        } catch (Exception e) {
            logger.error("error : ", e);
            tx.rollback();
        } finally {
            em.close();
        }

    }
}
