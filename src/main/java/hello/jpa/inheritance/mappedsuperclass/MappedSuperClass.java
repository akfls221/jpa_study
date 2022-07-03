package hello.jpa.inheritance.mappedsuperclass;

import hello.jpa.inheritance.join.Album;
import hello.jpa.inheritance.join.Book;
import hello.jpa.inheritance.join.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * @MappedSuperClass
 * 객체들이 주로 사용하는 공통 매핑 정보를 정의함.
 * 자식 엔티티들은 상속을 통해 매핑 정보를 물려 받을 수 있으며, BaseEntity는 테이블과 매핑할 필요가 없고 자식 엔티티에게 공통으로 사용되는 정보만 제공함.
 * 부모로부터 물려받은 매핑 정보를 재정의 하려면 @AttributeOverrides나 @AttributeOverride를 사용
 * 연관관계를 재정의 하려면 @AssociationOverrides 혹은 @AssociationOverride 사용
 * 참고 : 엔티티는 엔티티 이거나, @MappedSuperclass로 지정한 클래스만을 상속 가능함.
 */
public class MappedSuperClass {

    private final static Logger logger = LoggerFactory.getLogger(MappedSuperClass.class);

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("practice");
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        /**
         * 등록 Query
         */
        Member member = new Member();
        member.setName("엄태권");
        member.setEmail("akfls221@gmail.com");

        Seller seller = new Seller();
        seller.setName("짐박스");
        seller.setShopNumber("02-123-4567");

        em.persist(member);
        em.persist(seller);
        tx.commit();
    }
}
