package hello.jpa.jpql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.List;

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


            /**
             * getResultList()
             * 결과를 예제로 반환, 만약 결과가 없으면 빈 컬렉션을 반환
             */
            List<Member> resultList = em.createQuery("select m from Member m where m.name = '엄태권'", Member.class)
                    .getResultList();

            /**
             * getSingleResult()
             * 결과가 정확히 하나일 때 사용
             * 결과가 없으면 javax.persistence.NoResultException 예외가 발생
             * 결과과 1개보다 많으면 javax.persistence.NonUniqueResultException 발생
             */
            Member singleResult = em.createQuery("select m from Member m where m.name = '엄태권'", Member.class)
                    .getSingleResult();


            /**
             * 파라미터 바인딩
             * @이름 기준 파라미터 바인등
             * 파라미터를 이름으로 구분하는 방법이며, 앞에 :을 사용
             */
            String paramName = "엄태권";
            em.createQuery("select m from Member m where m.name =: username")
                    .setParameter("username", paramName)
                    .getSingleResult();

            /**
             * 위치 기준 파라미터
             * 위치 기준 파리미터를 사용하려면 ? 다음에 위치 값을 주면 된다.(1부터 시작함)
             * #참고 : 위치 기준 파리미터 방식보다는 이름 기준 파리미터 바인딩 방식을 사용하는 것이 더 명확함.
             */
            em.createQuery("select m from Member m where m.name =?1", Member.class)
                    .setParameter(1, paramName)
                    .getSingleResult();

            tx.commit();
        } catch (Exception e) {
            logger.error("error : ", e);
            tx.rollback();
        } finally {
            em.close();
        }

    }
}
