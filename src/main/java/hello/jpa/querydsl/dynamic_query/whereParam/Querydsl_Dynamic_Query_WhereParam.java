package hello.jpa.querydsl.dynamic_query.whereParam;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
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
 * Dynamic Query
 * where 절에 null이 되면 그냥 무시되기 때문에 이를 이용한 동적 쿼리 작성
 * @장점 : 메서드를 다른 쿼리에서도 재활용 할 수 있으며, 가독성이 높아짐.
 **/
public class Querydsl_Dynamic_Query_WhereParam {

    private final static Logger logger = LoggerFactory.getLogger(Querydsl_Dynamic_Query_WhereParam.class);

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

        String userName = "엄태권2";
        int age = 30;

        List<Member> members = searchMember_BooleanBuilder(userName, age);
        for (Member member1 : members) {
            logger.info("where절의 파라미터가 null일경우를 이용한 동적 쿼리 : {}", member1);
        }
        tx.commit();
    }


    public static List<Member> searchMember_BooleanBuilder(String name, int age) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("practice");
        EntityManager em = factory.createEntityManager();
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        return queryFactory
                .selectFrom(member)
                .where(usernameEq(name), ageEq(age))
                .fetch();
    }

    /**
     * BooleanExpression
     * BooleanExpression은 where에서 사용할 수 있는 값인데, 이 값은 ,를 and조건으로 사용합니다.
     * 여기에 Querydsl의 where는 null이 파라미터로 올 경우 조건문에서 제외합니다.
     */

    public static BooleanExpression usernameEq(String name) {
        return name == null ? null : member.name.eq(name);
    }

    public static BooleanExpression ageEq(Integer age) {
        return age == null ? null : member.age.goe(age);
    }

}
