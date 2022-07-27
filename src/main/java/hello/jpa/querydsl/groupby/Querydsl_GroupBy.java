package hello.jpa.querydsl.groupby;

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
 * GroupBy
 */
public class Querydsl_GroupBy {

    private final static Logger logger = LoggerFactory.getLogger(Querydsl_GroupBy.class);

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("practice");
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        tx.begin();

        //Test용 Member 생성 100개
        for (int i = 0; i < 100; i++) {
            em.persist(new Member("엄태권" + i, 29 + i));
        }
        em.flush();
        em.clear();

        List<Member> fetchResult = queryFactory
                .selectFrom(member)
                .groupBy(member.age)
                .having(member.age.goe(40))
                .fetch();

        for (Member eachMember : fetchResult) {
            logger.info("this is group by that member.age bigger then 40 : {}", eachMember);
        }


        tx.commit();
    }
}
