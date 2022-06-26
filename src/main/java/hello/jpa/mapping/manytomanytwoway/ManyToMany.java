package hello.jpa.mapping.manytomanytwoway;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * N:N 양방향
 * 다대다 양방향 관계
 * @JoinTable 을사용 하여 연결 테이블을 매핑
 * @JoinTable.name : 연결 테이블 이름 지정
 * @JoinTable.joinColumns : 현재 방향인 Member와 매핑할 조인 컬럼 정보 지정
 * @JoinTable.inverseJoinColumns : 반대 방향인 상품 과 매핑할 조인 컬럼 정보 지정
 * mapppedBy로 연관관계의 주인을 지정한다.
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

        Product findProduct = em.find(Product.class, 1L);
        for (Member findMember : findProduct.getMemberList()) {
            System.out.println("findMember name = " + findMember.getName());
        }


    }
}
