package hello.jpa.inheritance.singletable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * @Inheritance를 통해 상속 매핑전략을 선택함.(single table)
 * @DiscriminatorColumn을 통해 부모클래스에 구분 컬럼을 지정함.
 * 조회할 때 조인을 사용하지 않으므로 일반적으로 가장 빠름
 * 단점 : 자식 엔티티가 매핑한 컬름은 모두 null을 허용해야함.(사용하지 않는 컬럼이 있기 때문)
 * 테이블 하나에 모든 것을 통합하므로 구분 컬럼을 필수로 사용해야 함.
 * @DiscriminatorValue를 지정하지 않으면 기본적으로 엔티티 이름을 사용함.
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
