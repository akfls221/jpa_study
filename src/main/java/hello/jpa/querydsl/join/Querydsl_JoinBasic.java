package hello.jpa.querydsl.join;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import java.util.List;

import static hello.jpa.practice1.QOrder.*;
import static hello.jpa.practice1.QMember.*;
import static hello.jpa.practice1.QOrderItem.*;

/**
 * join(연관관계 있는 join)
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

        OrderMember orderMember = new OrderMember();
        orderMember.setName("엄태권");
        orderMember.setAge(29);
        em.persist(orderMember);

        Order orders = new Order();
        orders.addItem(orderItems);
        orders.setMember(orderMember);
        em.persist(orders);

        em.flush();
        em.clear();

        List<?> findList = queryFactory
                .selectFrom(order)
                .join(order.member, member)
                .leftJoin(order.orderItemList, orderItem)
                .fetch();

        for (Object o : findList) {
            logger.info("this is join : {}", o);
        }
        tx.commit();
    }
}
