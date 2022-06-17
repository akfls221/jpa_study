package hello.jpa.entity;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

//@Entity(DB 테이블 생성 방지를 위한 주석)
@NoArgsConstructor
@SequenceGenerator(
        name = "BOARD_SEQ",
        initialValue = 1, allocationSize = 1 //기본값 50
)
public class GenratorId {

    @Id
    /**
     * IDENTITY(Mysql, PostgreSQL 등)
     *  1) Entity를 DB에 먼저 저장
     *  2) 식별자를 DB에서 조회
     *  3) 조회한 식별자를 엔티티에 할당 - 하이버네이트는 statement.getgeneratedKeys()를 사용하여 저장과 동시에 생성된 기본키를 받아옴.
     *
     * SEQUENCE(Oracle, H2 등)
     *  1) 먼저 DB에서 식별자를 조회해옴.(generator 옵션에 있는 시퀀스와 매핑)
     *  2) 엔티티에 해당 식별자를 할당 후 영속성 컨텍스트에 해당 엔티티를 저장
     *  3) Flush(플러시) 동작시 해당 엔티티를 데이터베이스에 저장. - 총 DB통신 2번
     *  참고 : @SequenceGenerator(allocationSize) : 최적화를 위해 50으로 했으며, 한번 접근시 50까지 메모리에 시퀀스값을 들고있어 DB통신을 줄여줌.
     *        INSERT 성능이 중요하지 않다면 1로 설정.
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BOARD_SEQ")
    private Long id;

    @Column(name = "name")
    private String username;

    private Integer age;

    //Enum타입 사용시 선언
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @Lob
    private String description;



    public GenratorId(String username, Integer age) {
        this.username = username;
        this.age = age;
    }

    public GenratorId(Long id, String username, Integer age) {
        this.id = id;
        this.username = username;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenratorId member = (GenratorId) o;
        return Objects.equals(id, member.id) && Objects.equals(username, member.username) && Objects.equals(age, member.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, age);
    }
}
