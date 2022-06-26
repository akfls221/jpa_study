package hello.jpa.mapping.manytomanyoneway;

import hello.jpa.mapping.manytoonetwoway.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

/**
 * N:N 단방향
 * 다대다 단방향 관계
 * @JoinTable 을사용 하여 연결 테이블을 매핑
 * @JoinTable.name : 연결 테이블 이름 지정
 * @JoinTable.joinColumns : 현재 방향인 Member와 매핑할 조인 컬럼 정보 지정
 * @JoinTable.inverseJoinColumns : 반대 방향인 상품 과 매핑할 조인 컬럼 정보 지정
 */
public class ManyToMany {

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("practice");
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        Product product = new Product("product1");
        Product product2 = new Product("product2");
        Member member = new Member("taekwon");
        tx.begin();
        em.persist(product);
        em.persist(product2);
        member.getProduct().add(product);
        member.getProduct().add(product2);
        em.persist(member);

        tx.commit();
        em.clear();

        Member findMember = em.find(Member.class, 3L);
        for (Product findProduct : findMember.getProduct()) {
            System.out.println("findProduct name = " + findProduct.getName());
        }


    }
}
