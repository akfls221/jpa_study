package hello.jpa.jpql.projection;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Data
public class Member {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private int age;

    @Embedded
    private Address address;

    public Member(String name) {
        this.name = name;
    }
}
