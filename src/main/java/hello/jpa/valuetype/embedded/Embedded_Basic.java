package hello.jpa.valuetype.embedded;

import hello.jpa.cascase.persist.Child;
import hello.jpa.cascase.persist.Parent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Embedded
 * 단순하게 풀어진 정보들을 하나로 응집시킨다고 생각하면 됨.
 * 새로 정의한 EMbedded 값 타입들은 재사용할 수 있고, 응집도도 높음.
 * 또한 해당 값 타입만 사용하는 의미 있는 메소드를 만들 수 있음.
 * 임베디드 타입 사용시 아래 두개의 어노테이션이 필요하며, 둘 중 하나는 생략이 가능함.
 * @Embeddable : 값 타입을 정의하는 곳에서 표시
 * @Embedded : 값 타입을 사용하는 곳에서 표시
 */
public class Embedded_Basic {

    private final static Logger logger = LoggerFactory.getLogger(Embedded_Basic.class);

    public static void main(String[] args){

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("practice");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            //Member 생성
            Member member = new Member("엄태권", 29);

            //주소 생성
            Address address = new Address();
            address.setCity("서울");
            address.setZipcode("123123");
            address.setStreet("망양로");

            //기간 생성
            Period period = new Period();
            period.setStartDate(LocalDateTime.now());
            period.setEndDate(LocalDateTime.now());

            member.setHomeAddress(address);
            member.setWorkPeriod(period);

            em.persist(member);


            tx.commit();
        } catch (Exception e) {
            logger.error("error : ", e);
            tx.rollback();
        } finally {
            em.close();
        }

    }
}
