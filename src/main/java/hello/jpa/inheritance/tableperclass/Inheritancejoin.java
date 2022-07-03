package hello.jpa.inheritance.tableperclass;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * @Inheritance를 통해 상속 매핑전략을 선택함.(table per class)
 * @DiscriminatorColumn 사용하지 않음.
 * single table에서 단점이던 no null 제약조건을 하용할 수 있음.
 * 단점 : 자식 테이블을 통합해서 쿼리하기 어려움., 여러 자식 테이블을 함께 조회할 때 성능이 느림.
 * 주의 : 데이터베이스 설계자와 ORM 전문가 둘 다 추천하지 않는 전략으로 단일 테이블 혹은 조인 전략을 고려하자!
 */
public class Inheritancejoin {

    private final static Logger logger = LoggerFactory.getLogger(Inheritancejoin.class);

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
        book.setName("JPA ORM");
        book.setPrice(10000);

        Album album = new Album();
        album.setArtist("오아시스");
        album.setName("오아시스다요");
        album.setPrice(100000);

        Movie movie = new Movie();
        movie.setActor("김다미");
        movie.setDirector("감독");
        movie.setPrice(2000);
        movie.setName("마녀2");

        em.persist(book);
        em.persist(album);
        em.persist(movie);

        tx.commit();
    }
}
