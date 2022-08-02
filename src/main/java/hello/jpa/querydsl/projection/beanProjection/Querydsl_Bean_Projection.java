package hello.jpa.querydsl.projection.beanProjection;

import com.querydsl.core.Tuple;
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
 * bean Projection
 **/
public class Querydsl_Bean_Projection {

    private final static Logger logger = LoggerFactory.getLogger(Querydsl_Bean_Projection.class);

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
         * 프로퍼티를 통한 프로젝션 입력
         * com.mysema.query.Projections 를 사용함.
         * @참고 : new Instance로 생성하기 때문에 기본 생성자가 필수임.(setter를 통해 값을 채우기 때문에 setter 필요함) -> 기본생성자, setter 필수
         * 쿼리 결과와 매핑할 프로퍼티 이름이 다르면 as를 통해 별칭을 줄 수 있음.
         * ex : item.name.as("username")
         */
        List<SelectDto> result = queryFactory
                .select(Projections.bean(SelectDto.class, member.name, member.age))
                .from(member)
                .fetch();

        for (SelectDto selectDto : result) {
            logger.info("프로퍼티를 통한 프로젝션 가져오기 : {}", selectDto);
        }


        tx.commit();
    }
}
