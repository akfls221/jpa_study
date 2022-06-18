package hello.jpa.step2;

import javax.persistence.*;

public class MappingSave {

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("practice");
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            TestTeam team = new TestTeam("teamA");
            em.persist(team);

            TestMember member1 = new TestMember("member1");
            member1.setTeam(team);
            em.persist(member1);

            TestMember member2 = new TestMember("member2");
            member2.setTeam(team);
            em.persist(member2);


            tx.commit();

        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        factory.close();

    }
}
