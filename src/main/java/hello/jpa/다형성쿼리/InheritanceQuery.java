package hello.jpa.다형성쿼리;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

/**
 * @Inheritance Query
 * JPQL로 부모 엔티티를 조회하면 그 자식 엔티티도 함께 조회함.
 */
public class InheritanceQuery {

    private final static Logger logger = LoggerFactory.getLogger(InheritanceQuery.class);

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("practice");
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        /**
         * 등록 Query
         */
        Book book = new Book();
        book.setAuthor("김영한");
        book.setIsbn(123123);

        Album album = new Album();
        album.setArtist("오아시스");

        em.persist(book);
        em.persist(album);
        em.flush();
        em.clear();

        List resultList = em.createQuery("select i from Item i").getResultList();
        for (Object o : resultList) {
            logger.info("다형성 쿼리 Item으로 조회시 자식(Book, Album) 조회 : {}", o);
        }

        /**
         * Type
         * TYPOE은 엔티티 상속 구조에서 조회 대상을 특정 자식 타입으로 한정할 때 주로 사용함.
         * 예) Item 중에서 Book, Album 엔티티로 조회하라
         */
        List typeResult = em.createQuery("select i from Item i where type(i) IN (Book, Album)").getResultList();
        for (Object o : typeResult) {
            logger.info("다형성 쿼리 Item으로 조회시 type 지정(Book, Album) 조회 : {}", o);
        }

        /**
         * TREAT
         * JPA 2.1 에서 추가된 가능으로 자바의 '타입 캐스팅'과 비슷함.
         * 즉 부모 타입을 특정 자식 타입으로 다룰때 사용함(Item -> Book)
         * Item을 자식 타입인 Book으로 다뤄 author 필드를 가져오기
         * @참고 : JPA 표준은 FROM, WHERE절에서 사용할 수 있지만, 하이버네이트는 SELECT 절에서도 사용가능
         */
        Item treatItemToBook = em.createQuery("select i from Item i where treat(i as Book).author = '김영한'", Item.class)
                .getSingleResult();

        logger.info("TREAT를 사용하여 Item -> Book 그리고 Book의 필드에 접근 : {}", treatItemToBook);

        Object treatItemToBook_select = em.createQuery("select treat(i as Book).author from Item i where treat(i as Book ).isbn = 123123")
                .getSingleResult();

        logger.info("TREAT를 사용하여 Item을 Book으로 바꿔 select절에서 사용해 보기 : {}", treatItemToBook_select);
        tx.commit();
    }
}
