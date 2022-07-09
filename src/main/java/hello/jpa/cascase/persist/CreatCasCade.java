package hello.jpa.cascase.persist;

import hello.jpa.fetchtype.lazy.Member;
import hello.jpa.fetchtype.lazy.Team;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * 영속성 전이 생성 (CASCADE)
 * 기존이라면 부모 엔티티 를 등록하고, 자식 엔티티를 등록한 후 persist 해야 하지만,
 * 부모에 자식을 set 한후 persist 를 진행시 자식까지 persist 되는 기능
 * 즉 엔티티를 영속화할 때 연관된 엔티티도 같이 영속화하는 쳔리함을 제공하는 기능일 뿐
 */
public class CreatCasCade {

    private final static Logger logger = LoggerFactory.getLogger(CreatCasCade.class);

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


            tx.commit();
        } catch (Exception e) {
            logger.error("error : ", e);
            tx.rollback();
        } finally {
            em.close();
        }

    }
}
