package hello.jpa.querydsl.projection.constructorProjection;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import hello.jpa.querydsl.Member;
import hello.jpa.querydsl.projection.SelectDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

import static hello.jpa.querydsl.QMember.member;

/**
 * Field Projection
 **/
public class Querydsl_Constructor_Projection {

    private final static Logger logger = LoggerFactory.getLogger(Querydsl_Constructor_Projection.class);

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
         * 생성자를 통한 Projection
         * DTO 객체가 바뀌어도 타입이 맞다면 괜찮음
         * 지정한 프로젝션과 파라미터 순서가 같은 생성자가 필요함. -> Projection 과 동일한 Type순서의 생성자 필수
         */
        List<SelectDto> result = queryFactory
                .select(Projections.constructor(SelectDto.class, member.name, member.age))
                .from(member)
                .fetch();

        for (SelectDto selectDto : result) {
            logger.info("프로퍼티를 통한 프로젝션 가져오기 : {}", selectDto);
        }


        tx.commit();
    }
}
