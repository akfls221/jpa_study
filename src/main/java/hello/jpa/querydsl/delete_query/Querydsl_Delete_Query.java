package hello.jpa.querydsl.delete_query;

import com.querydsl.jpa.impl.JPADeleteClause;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;
import hello.jpa.querydsl.Member;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

import static hello.jpa.querydsl.QMember.member;

/**
 * Delte Query
 * @참고 : 영속성 컨텍스트와 무관하게 DB에 바로 쿼리문이 입력됨.(즉 DB결과와 영속성의 내용이 다름)
 * DB 결과는 delete를 통해 2가 되어도, 영속성에서는 1로 되어있음.
 **/
public class Querydsl_Delete_Query {

    private final static Logger logger = LoggerFactory.getLogger(Querydsl_Delete_Query.class);

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("practice");
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        tx.begin();

        //Test용 Member 생성
        Member joinMember = new Member("엄태권", 29);
        Member joinMember1 = new Member("엄태권2", 30);
        Member joinMember2 = new Member("엄태권3", 31);
        Member joinMember3 = new Member("엄태권4", 32);
        Member joinMember4 = new Member("엄태권5", 33);
        em.persist(joinMember);
        em.persist(joinMember1);
        em.persist(joinMember2);
        em.persist(joinMember3);
        em.persist(joinMember4);

        /**
         * delete 쿼리문
         * @영속성 컨텍스트의 값과 비교
         * excute()를 통해 쿼리문 실행, 반환값 : delete 된 수를 return(long Type)
         **/
        //업데이트 실행
        queryFactory
                .delete(member)
                .where(member.name.eq("엄태권2"))
                .execute();

//        em.flush();
//        em.clear();

        //영속성 컨텍스트와 비교
        List<Member> result = queryFactory
                .selectFrom(member)
                .fetch();

        for (Member member1 : result) {
            logger.info("이름이 엄태권2인 행이 삭제되었으므로, 4개만 나와야함. : {}", member1);
        }

        /**
         * @추가사항 : JPAUpdateClause를 사용하는 경우도있음.
         */

        //파라미터 1 : entityManager, 파라미터2 : Entity(QClass)
        JPADeleteClause jpaDeleteClause = new JPADeleteClause(em, member);
        jpaDeleteClause
                .where(member.name.eq("엄태권3"))
                .execute();

        //영속성 컨텍스트와 DB 데이터를 맞추기 위한 초기화 작업
//        em.flush();
//        em.clear();

        List<Member> result2 = queryFactory
                .selectFrom(member)
                .fetch();

        for (Member member1 : result2) {
            logger.info("JPADeleteClause 사용 : {}", member1);
        }


        tx.commit();
    }
}
