package hello.jpa.valuetype.listtypeembedded;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;

/**
 * List Type Embedded
 * 값 타입을 하나 이상 저장하려면 컬렉션에 보관함.
 * @ElementCollection, @CollectionTable 어노테이션을 사용함.
 * 기본 값 타입 컬렉션(String) 은 영속성 전이(Cascade) + 고아 객체(ORPHAN REMOVE) 기능을 필수로 가진다.
 * 값 타입 컬렉션도 조회할 때 페치 전략을 사용할 수 있으며 기본 LAZY 방식임.
 */
public class ListEmbeddedType {

    private final static Logger logger = LoggerFactory.getLogger(ListEmbeddedType.class);

    public static void main(String[] args){

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("practice");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            //Member 생성
            Member member = new Member();
            member.setName("엄태권");

            //embedded 값 타입
            member.setHomeAddress(new Address("서울", "관악구"));
            //기본 값 타입 컬렉션(String)
            member.getFavoriteFoods().add("볶음밥");
            member.getFavoriteFoods().add("삼겹살");
            member.getFavoriteFoods().add("회");
            //임베디드 값 타입 컬렉션(Embedded)
            member.getAddressHistory().add((new Address("부산", "창원시")));
            member.getAddressHistory().add((new Address("울산", "울산시")));

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
