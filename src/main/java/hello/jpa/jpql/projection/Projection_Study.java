package hello.jpa.jpql.projection;

import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * selct 절에서 조회할 대상을 지정하는 것을 프로젝션 이라고 함.
 * 프로젝션 대상은 엔티티, 엠비디드 타입, 스칼라 타입이 있음.
 *
 * 조회한 엔티티는 영속석 컨텍스트에서 관리됨.
 *
 */
public class Projection_Study {

    private final static Logger logger = LoggerFactory.getLogger(Projection_Study.class);

    public static void main(String[] args){

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("practice");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            //Address 생성
            Address address = new Address();
            address.setCity("서울");
            address.setStreet("청룡로");

            //Member 생성
            Member member = new Member("엄태권");
            member.setAge(29);
            member.setAddress(address);
            em.persist(member);
            em.flush();
            em.clear();

            /**
             * 엔티티 프로젝션
             */
            Object selectMember = em.createQuery("select m from Member m").getSingleResult();
            logger.info("this is entity projection : {}", selectMember);

            /**
             * 임베디드 타입 프로젝션
             * JPQL에서 임베디드 타입은 엔티티와 거의 비슷하게 사용되지만, 임베디드 타입은 조회의 시작점이 될 수 없다는 제약이 있음.
             * 아래 잘못된 코드와 정상적인 코드를 참조
             */
            //잘못된 코드
            //em.createQuery("select a from Address a").getSingleResult();

            //정상적 코드(이렇게 엔티티를 통해서 임베디드 타입을 조회할 수 있음.)
            //@참조 : 임베디드 타입은 엔티티 타임이 아닌 값 타입으로 이렇게 직접 조회한 임베디드 타입은 영속성 컨텍스트에서 관리되지 않음.
            Object findAddress = em.createQuery("select m.address from Member m").getSingleResult();
            logger.info("this is Embedded projection : {}", findAddress);

            /**
             * 스칼라 타입 프로젝션
             * 숫자, 문자, 날짜와 같은 기본 데이터 타입들을 스칼라 타입이라고 함.
             */
            String selectMemberName = em.createQuery("select m.name from Member m", String.class).getSingleResult();
            logger.info("this is 스칼라 projection : {}", selectMemberName);


            /**
             * 여러 값 조회
             * 타입이 여러개인 값 조회
             *
             * @참고 : 프로젝션에 여러 값을 선택하면 TypeQuery를 사용할 수 없고 대신 Query를 사용해야 함.
             */
            //Query query = em.createQuery("select m.age, m.name from Member m");
            List<Object[]> resultList = em.createQuery("select m.age, m.name from Member m").getResultList();
            for (Object[] objects : resultList) {
                logger.info("this is many type value projection : {}, {}", objects[0], objects[1]);
            }

            /**
             * New 명령어
             * 보통 위처럼 여러 타입의 값을 받는 경우 Object[]를 사용하기보단 UserDTO 처럼 의미 있는 객체로 변환해서 사용함.
             * 2단계로 나누어 작성 예정이며, 하나는 DTO 타입으로 변환하는 부부분과,
             * 다른 하나는 New를 통해 직접 DTO로 받는 부분이다.
             *
             * @참고 : 패키지 명을 포함한 전체 클래스 명을 입려개향 함, 순서와 타입이 일치하는 생성자가 필요함.
             */
            //UserDTO 생성(패키지에 UserDto 생성

            //1. Object[] -> UserDto
            List<Object[]> objectResult = em.createQuery("select m.name, m.age from Member m").getResultList();
            List<UserDto> userDtoList = new ArrayList<>();
            for (Object[] objects : objectResult) {
                UserDto userDto = new UserDto((String) objects[0], (Integer) objects[1]);
                userDtoList.add(userDto);
            }
            for (UserDto userDto : userDtoList) {
                logger.info("this is userDto first step : {}", userDto);
            }

            //2. new 를 통한 UserDto 바로 받기(여기의 UserDto는 별도로 패키지 안에 클래스를 생성함)
            // new를 사용한 클래스로 typeQuery를 사용이 가능함.
            //TypedQuery<UserDto> query = em.createQuery("select new hello.jpa.jpql.projection.UserDto(m.name, m.age) from Member m", UserDto.class);
            List<UserDto> userDtoResult = em.createQuery("select new hello.jpa.jpql.projection.UserDto(m.name, m.age) from Member m", UserDto.class)
                    .getResultList();
            for (UserDto userDto2 : userDtoResult) {
                logger.info("this is userDto second step : {}", userDto2);
            }


            tx.commit();
        } catch (Exception e) {
            logger.error("error : ", e);
            tx.rollback();
        } finally {
            em.close();
        }

    }


}
