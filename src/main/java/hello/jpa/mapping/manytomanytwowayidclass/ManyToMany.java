package hello.jpa.mapping.manytomanytwowayidclass;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * 복합키를 이용한 구성(@IdClass 를 사용)
 * Serializable을 구현해야 함.
 * equals와 hashCode 메소드를 구현해야 함.
 * 기본생성자가 있어야 함.
 * 식별자 클래스는 public 이어야 함.
 * @EmmberddedId를 사용하는 방법도 있음.
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

        Product product = new Product("product1");
        em.persist(product);

        MemberProduct memberProduct = new MemberProduct();
        memberProduct.setMember(member);
        memberProduct.setProduct(product);
        memberProduct.setOrderAmount(20);
        em.persist(memberProduct);

        tx.commit();


        /**
         * 조회 Query
         */
        //기본 키 값 생성
        MemberProductId memberProductId = new MemberProductId();
        memberProductId.setMember(member.getId());
        memberProductId.setProduct(product.getId());

        MemberProduct findMemberProduct = em.find(MemberProduct.class, memberProductId);
        String findMember = findMemberProduct.getMember().getName();
        String findProduct = findMemberProduct.getProduct().getName();

        logger.info("findMember 이름 : {}", findMember);
        logger.info("findProduct 이름 : {}", findProduct);

    }
}
