package hello.jpa.querydsl.dynamic_query.boolean_builder;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPADeleteClause;
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
 * BooleanBuilder방식을 사용한 방법
 **/
public class Querydsl_Dynamic_Query {

    private final static Logger logger = LoggerFactory.getLogger(Querydsl_Dynamic_Query.class);

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

        BooleanBuilder builder = searchMember_BooleanBuilder(userName, age);
        List<Member> result = queryFactory
                .selectFrom(member)
                .where(builder)
                .fetch();
        for (Member member1 : result) {
            logger.info("동적쿼리 BooleanBuilder를 사용함 : {}", member1);
        }
        tx.commit();
    }


    public static BooleanBuilder searchMember_BooleanBuilder(String name, int age) {

        BooleanBuilder builder = new BooleanBuilder();
        if (!name.isBlank()) {
            builder.and(member.name.eq(name));
        }

        if (age > 30) {
            builder.and(member.age.goe(age));
        }

        return builder;
    }

}
