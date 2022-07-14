package hello.jpa.valuetype.attributeoverride;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;

/**
 * @AttributeOverrides
 * 매핑정보를 재정의하려면 엔티티에 @AttributeOverride를 사용함.
 * 테이블에 매핑하는 컬럼명이 중복되는 것을 방지할 수 있음.
 */
public class AttributeOverrides {

    private final static Logger logger = LoggerFactory.getLogger(AttributeOverrides.class);

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

            //회사주소 생성
            Address companyAddress = new Address();
            companyAddress.setCity("서울2");
            companyAddress.setZipcode("23333");
            companyAddress.setStreet("메로나");

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
