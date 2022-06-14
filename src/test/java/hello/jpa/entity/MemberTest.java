package hello.jpa.entity;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest //테스트 방식 변경 예정
class MemberTest {

    @PersistenceContext
    EntityManager em;

    @Test
    @Transactional
    void basicTest() {
        Member member = new Member(1L, "taekwon", 11);

        //등록
        em.persist(member);
        log.info("[member persist] {}", member);
        
        //수정
        member.setAge(20);
        member.setUsername("Hello JPA");
        log.info("[member modify] {}", member);

        //삭제
        em.remove(member);
        
        //삭제
        
    }

}