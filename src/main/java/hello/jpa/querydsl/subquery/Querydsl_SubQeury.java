package hello.jpa.querydsl.subquery;

import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import hello.jpa.querydsl.Member;
import hello.jpa.querydsl.QMember;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

import static hello.jpa.querydsl.QMember.member;

/**
 * SubQeury
 * 안에 있는 select 문의 객체가 바깥의 객체와 겹치면 안되기 때문에 as를 별도로 만들어 줘야함.
 * QMember memberSub = new QMember("memberSub")
 */
public class Querydsl_SubQeury {

    private final static Logger logger = LoggerFactory.getLogger(Querydsl_SubQeury.class);

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
        em.flush();
        em.clear();

        QMember memberSub = new QMember("memberSub");

        List<Member> subResult = queryFactory
                .select(member)
                .from(member)
                .where(member.age.eq(JPAExpressions
                        .select(memberSub.age.max())
                        .from(memberSub))
                )
                .fetch();

        for (Member findMember : subResult) {
            logger.info("this is Querydsl subQuery : {}", findMember);
        }


        tx.commit();
    }
}
