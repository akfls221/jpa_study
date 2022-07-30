package hello.jpa.querydsl.join;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import java.util.List;

import static hello.jpa.querydsl.join.QOrder.*;
import static hello.jpa.querydsl.join.QOrderMember.*;
import static hello.jpa.querydsl.join.QOrderItem.*;
import static hello.jpa.querydsl.join.QOtherMember.*;

/**
 * join(연관관계 있는 join)
 * join(조인 대상, 별칭으로 사용할 쿼리 타입(QClass)
 * 조인의 기본 문법은 첫 번째 파라미터에 조인 대상을 지정하고, 두번쨰 파라미터에 별칭으로 사용할 Q 타입을 지정하면 된다.
 */
public class Querydsl_JoinBasic {

    private final static Logger logger = LoggerFactory.getLogger(Querydsl_JoinBasic.class);

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("practice");
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        tx.begin();
        OrderItem orderItems = new OrderItem();
        orderItems.setItemName("상품1");
        em.persist(orderItems);

        OrderMember member = new OrderMember();
        member.setName("엄태권");
        member.setAge(29);
        em.persist(member);

        Order orders = new Order();
        orders.addItem(orderItems);
        orders.setOrderMember(member);
        em.persist(orders);

        em.flush();
        em.clear();

        /**
         * 기본 on절
         * order0_.member_id=ordermembe1_.member_id 자동으로 나감
         */
        List<?> findList = queryFactory
                .selectFrom(order)
                .join(order.orderMember, orderMember)
                .leftJoin(order.orderItemList, orderItem)
                .fetch();

        for (Object o : findList) {
            logger.info("this is join : {}", o);
        }

        /**
         * on 절 포함한 join
         */
        List<Order> result = queryFactory
                .selectFrom(order)
                .join(order.orderMember, orderMember)
                .leftJoin(order.orderItemList, orderItem)
                .on(orderMember.name.eq("엄태권"))
                .fetch();
        for (Order orderResult : result) {
            logger.info("this is join... on where orderMember name = 엄태권 : {}", orderResult);
        }

        /**
         * 연관관계 없는 조인
         * select 대상 지정후, from에 조회할 엔티티와 연관관계가 없는 엔티티를 넣음.(연관관계가 없기 때문에 두군대서 조회)
         * where조건으로 join  실행
         */
        OtherMember member1 = new OtherMember();
        member1.setName("엄태권");
        em.persist(member1);

        OrderMember memberResult = queryFactory
                .select(orderMember)
                .from(orderMember, otherMember)
                .where(orderMember.name.eq("엄태권"))
                .fetchOne();

        logger.info("연관관계가 없는 엔티티 조인 : {}", memberResult);

        tx.commit();
    }
}
