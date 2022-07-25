package hello.jpa.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import java.util.List;

import static hello.jpa.querydsl.QMember.member;

/**
 * gradle > task > other > compileQuerydsl 을 통해 QClass 생성
 */
public class Querydsl_Basic {

    private final static Logger logger = LoggerFactory.getLogger(Querydsl_Basic.class);

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("practice");
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        tx.begin();

        //Test용 Member 생성
        Member joinMember = new Member("엄태권", 29);
        em.persist(joinMember);
        em.flush();
        em.clear();

        List<Member> findResultWithQuerydsl = queryFactory
                .select(member)
                .from(member)
                .where(member.name.eq("엄태권"))
                .fetch();

        for (Member findMember : findResultWithQuerydsl) {
            logger.info("this is Querydsl Basic : {}", findMember);
        }


        tx.commit();
    }
}
