package hello.jpa.cascase.orphan;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * 부모 엔티티와 연관관계가 끊어진 자식 엔티티를 자동으로 삭제하는 기능을 제공함.
 * 이를 고아 객체 제거 라고 하며, 이 기능을 사용해서 부모 엔티티의 컬렉션에서
 * 자식 엔티티의 참조만 제거한다면, 자동으로 자식엔티티가 삭제됨.
 * 참고로 고아 객체 제거 기능은 영속성 컨텍스트를 플러시 할 때 적용됨으로 플러시 시점에 DELETE SQL이 실행 됨.
 * 옵션 : orphanRemoval = true
 *
 * 주의 : 고아 객체 제거는 참조가 제거된 엔티티는 다른 곳에서 참조하지 않는 고아 객체로 보고 삭제함.(따라서 참조하는 곳이 하나일 때만 사용해야 함.)
 * @OneToOne / @OneToMany 에서만 사용가능함.
 */
public class OrphanStudy {

    private final static Logger logger = LoggerFactory.getLogger(OrphanStudy.class);

    public static void main(String[] args){

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("practice");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            //Child 생성
            Child child1 = new Child("2", "child1");
            Child child2 = new Child("3", "child2");

            //Parent 생성
            Parent parent = new Parent("1", "parent");
            child1.setParent(parent);
            child2.setParent(parent);
            em.persist(parent);
            
            //Orphan Test
            parent.getChildList().remove(0);


            tx.commit();
        } catch (Exception e) {
            logger.error("error : ", e);
            tx.rollback();
        } finally {
            em.close();
        }

    }
}
