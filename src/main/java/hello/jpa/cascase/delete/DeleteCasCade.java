package hello.jpa.cascase.delete;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class DeleteCasCade {

    private final static Logger logger = LoggerFactory.getLogger(DeleteCasCade.class);

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
            
            //Cascade Delete
            em.remove(parent);


            tx.commit();
        } catch (Exception e) {
            logger.error("error : ", e);
            tx.rollback();
        } finally {
            em.close();
        }

    }
}
