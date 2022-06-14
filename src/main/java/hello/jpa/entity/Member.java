package hello.jpa.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Data
@EqualsAndHashCode
public class Member {

    @Id
    private Long id;

    @Column(name = "name")
    private String username;

    private Integer age;

    public Member(String username, Integer age) {
        this.username = username;
        this.age = age;
    }

    public Member(Long id, String username, Integer age) {
        this.id = id;
        this.username = username;
        this.age = age;
    }
}
