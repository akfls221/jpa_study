package hello.jpa.fetchtype.eager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * fetchType Eager
 * - Member 객체를 조회해 올때 join을 통해 Team 객체도 한번에 가져옴.
 * - 즉시 로딩을 최적화하기 위해 가능하면 조인쿼리 사용
 * - NULL 제약조건 (기본적으로 JPA는 NULL일 경우 데이터를 아이에 못가져오는 상황을 대비하여 외부조인을 하여 가져옴. 그러나 NOT NULL 이 명시적인 경우 내부 조인을 함.)
 * - @JoinColumn(nullabe = true) :  NULL 허용(기본값), 외부조인 사용
 * - @JoinColumn(nullabe = false) :  NULL 허용하지 않음., 내부조인 사용
 * - @ManyToOne(fetch = FetchType.EAGER. optional = false) 사용시 내부조인
 */
public class EagerFetch {

    private final static Logger logger = LoggerFactory.getLogger(EagerFetch.class);

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
            em.clear(); //DB에 데이터 저장후 clear

            //외부조인으로 Team과 함께 가져온 후 Team을 반환
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
