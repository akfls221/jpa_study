package hello.jpa.jpql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;

/**
 * JPQL
 * 객체지향 쿼리언어로, 테이블을 대상으로 쿼리하는 것이 아니라 엔티티 객체를 대상으로 쿼리함.
 * SQL을 추상화해서 특정 데이터베이스 SQL에 의존하지 않음.
 * 결국 SQL로 변함.
 * 참고 : 엔티티를 저장할 때는 ENtityManager.persist() 메소드를 사용하면 되므로 INSERT 문은 없음.
 */
public class Jpql_Basci {

    private final static Logger logger = LoggerFactory.getLogger(Jpql_Basci.class);

    public static void main(String[] args){

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("practice");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            //Member 생성
            Member member = new Member("엄태권", 29);
            em.persist(member);
            em.flush();
            em.clear();

            /**
             * SELECT 문
             * 대소문자 구분 : 엔티티와 속성은 대소문자를 구분함, JPQL 키워드는 대소문자를 구분하지 않음.
             * 엔티티 이름 : 클래스 명으로 착각하지만 엔티티 명으로 사용함.(엔티티 명은 @Entity(name="@@@")으로 사용가능) 지정하지 않을경우 클래스명사용(권장)
             * 별칭은 필수 : AS 라는 별칭을 필수로 주어야 하며, 생략이 가능함.
             */
            Member result = em.createQuery("select m from Member m where m.name = '엄태권'", Member.class)
                    .getSingleResult();

            logger.info("select 문 조회 결과 : {}", result.getName());

            /**
             * TypeQuery
             * 반환하는 타입이 명확한 경우 사용가능(2번째 파라미터를 지정시)
             */
            TypedQuery<Member> query = em.createQuery("select m.name from Member m where m.name = '엄태권'", Member.class);

            /**
             * Query
             * 반환하는 타입이 명확하지 않은 경우
             */
            Query query1 = em.createQuery("select m.name from Member m where m.name = '엄태권'");


            tx.commit();
        } catch (Exception e) {
            logger.error("error : ", e);
            tx.rollback();
        } finally {
            em.close();
        }

    }
}
