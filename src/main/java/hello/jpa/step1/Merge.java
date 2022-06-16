package hello.jpa.step1;

import hello.jpa.entity.Member;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;

public class Merge {
    /**
     * 준 영속상태의 엔티티를 다시 영속 상태로 변경
     * 새로운 영속 상태의 엔티티를 반환
     */

    static EntityManagerFactory factory = Persistence.createEntityManagerFactory("practice");
    private final static Logger logger = LoggerFactory.getLogger(Detach.class);

    public static void main(String[] args) {

        Member member = createMember(2L, "회원1");
        
        member.setUsername("회원명변경"); //준영속 상태이기 때문에 DB 반영 X

        mergeMember(member);

    }

    private static void mergeMember(Member member) {
        EntityManager em2 = factory.createEntityManager();
        EntityTransaction tx2 = em2.getTransaction();

        tx2.begin();
        Member mergeMember = em2.merge(member); //영속상태로 변경
        //member = em2.merge(member); 준영속 엔티티를 참조하던 변수를 다시 영속 엔티티를 참조하도록 하는것이 안전하다. 현재는 테스트를 위해 적용 안함.
        tx2.commit();

        logger.info("member = " + member.getUsername()); //준영속 상태 > member의 경우 createMember에서 준영속상태로 return
        logger.info("mergeMember = " + mergeMember.getUsername()); // 영속상태(merge를 통해 새로운 영속상태의 엔티티 반환됨)

        /**
         * contains
         * 영속성 컨텍스트가 파라미터로 넘어온 엔티티를 관리하는지 확인하는 메서드
         * 영속상태인지 체크
         */
        logger.info("em2.contains(member) = " + em2.contains(member)); //준영속 false
        logger.info("em2.contains(mergeMember) = " + em2.contains(mergeMember)); //영속 true


    }

    private static Member createMember(Long id, String username) {
        EntityManager em1 = factory.createEntityManager();
        EntityTransaction tx1 = em1.getTransaction();
        tx1.begin();

        Member member = new Member();
        member.setId(id);
        member.setUsername(username);

        em1.persist(member);
        tx1.commit();

        em1.close(); //영속성 컨텍스트 1 종료 > Member는 준영속

        return member;
    }
}
