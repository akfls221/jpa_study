package hello.jpa.querydsl.projection;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import hello.jpa.querydsl.Member;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import static hello.jpa.querydsl.QMember.member;

/**
 * Projection
 * select 절에 조회 대상을 지정하는 것을 프로젝션 이라고 함.
 **/
public class Querydsl_Projection {

    private final static Logger logger = LoggerFactory.getLogger(Querydsl_Projection.class);

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

        /**
         * 프로젝션 대상이 하나일 경우
         */

        String oneProjection = queryFactory
                .select(member.name)
                .from(member)
                .where(member.age.eq(29))
                .fetchOne();

        logger.info("this is Querydsl one Projection : {}", oneProjection);

        /**
         * 둘 이상의 타입이 있는 프로젝션
         * Tuple로 반환 되며, querydsl에서 지원하는 타입으로, repository에서만 사용하고, 서비스 단계 까지 가져가지 않는것이 좋음.
         */

        Tuple twoProjection = queryFactory
                .select(member.name, member.age)
                .from(member)
                .where(member.age.eq(30))
                .fetchOne();

        logger.info("this is Querydsl two Projection name: {}, age : {}", twoProjection.get(member.name), twoProjection.get(member.age));

        /**
         * 둘 이상의 타입 DTO로 받기
         * DTO package 이름을 다 적어야 하는 지저분함이 있음.
         * 생성자 방식만 지원
         * JPQL로 받아야함.
         */

        SelectDto singleResult = em.createQuery("select new hello.jpa.querydsl.projection.SelectDto(m.name, m.age) from Member m " +
                        "where m.age = 30", SelectDto.class)
                .getSingleResult();

        logger.info("this is JPQL select By Dto : {}", singleResult);

        tx.commit();
    }
}
