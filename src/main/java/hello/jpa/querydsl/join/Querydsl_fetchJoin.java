package hello.jpa.querydsl.join;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

import static hello.jpa.querydsl.join.QOrder.order;
import static hello.jpa.querydsl.join.QOrderItem.orderItem;
import static hello.jpa.querydsl.join.QOrderMember.orderMember;
import static hello.jpa.querydsl.join.QOtherMember.otherMember;

/**
 * fetchjoin
 * 기본적으로 fetch.LAZY 를 기준으로 함.
 * lazy의 경우 해당 엔티티에 접근할 경우에 쿼리가 한번더 날아가는 형태임
 * Querydsl에서 fetchjoin()을 이용하여 Eager처럼 해당 엔티티를 join하여 가져올 수 있음.
 */
public class Querydsl_fetchJoin {

    private final static Logger logger = LoggerFactory.getLogger(Querydsl_fetchJoin.class);

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
        orders.setName("테스트");
        em.persist(orders);

        em.flush();
        em.clear();

        /**
         * fetch join
         */
        Order findOrder = queryFactory
                .selectFrom(order)
                .where(order.name.eq("테스트"))
                .fetchOne();

        boolean loaded = factory.getPersistenceUnitUtil().isLoaded(findOrder.getOrderMember());
        logger.info("초기화 되지 않은 엔티티, 로딩되지 않은 엔티티 : {}", loaded);

        Order fetchOrder = queryFactory
                .selectFrom(order)
                .join(order.orderMember, orderMember).fetchJoin()
                .where(order.name.eq("테스트"))
                .fetchOne();

        boolean trueLoaded = factory.getPersistenceUnitUtil().isLoaded(fetchOrder.getOrderMember());
        logger.info("초기화 된 엔티티, 로딩된 엔티티 : {}", trueLoaded);


        tx.commit();
    }
}
