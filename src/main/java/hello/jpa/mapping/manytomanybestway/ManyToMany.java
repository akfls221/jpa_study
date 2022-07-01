package hello.jpa.mapping.manytomanybestway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 복합키의 경우 항상 식별자 클래스를 만들어야 하는 불편함이 있음.
 * 추천하는 기본 키 생성 전략은 데이터베이스에서 자동으로 생성해 주는 대리키를 사용하는 것임.
 * 추가로 복합키륾 만들지 않아도 되어 편리함.(설계의 문제일것으로 보임)
 */
public class ManyToMany {

    private final static Logger logger = LoggerFactory.getLogger(ManyToMany.class);

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("practice");
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        /**
         * 등록 Query
         */
        Member member = new Member("member");
        em.persist(member);

        Product product = new Product("product");
        em.persist(product);

        Orders orders = new Orders();
        orders.setOrderAmount(10);
        orders.addMember(member);
        orders.setOrderDate(LocalDateTime.now());
        orders.setProduct(product);
        em.persist(orders);

        tx.commit();


        /**
         * 조회 Query
         */
        //기본 키 값 생성
        em.find(Orders.class, 3L);
        String findMember = orders.getMember().getName();
        String findProduct = orders.getProduct().getName();

        List<Orders> ordersList = em.find(Member.class, 1L).getOrdersList();
        for (Orders findOrder : ordersList) {
            logger.info("findOrderList amount : {}" ,findOrder.getOrderAmount());
        }

        logger.info("findMember 이름 : {}", findMember);
        logger.info("findProduct 이름 : {}", findProduct);

    }
}
