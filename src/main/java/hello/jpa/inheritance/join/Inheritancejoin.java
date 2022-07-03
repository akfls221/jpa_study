package hello.jpa.inheritance.join;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * @Inheritance를 통해 상속 매핑전략을 선택함.(join)
 * @DiscriminatorColumn을 통해 부모클래스에 구분 컬럼을 지정함.
 * 기본 값으로 자식 테이블은 부모 테이블의 ID 컬럼명을 그대로 사용하나 자식 테이블의 기본 키 컬럼을 변경하려면 @PrimaryKeyJoinColumn을 사용함.
 * 단점 : 데이터를 등록한 INSERT SQL을 두 번 실행함.
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
