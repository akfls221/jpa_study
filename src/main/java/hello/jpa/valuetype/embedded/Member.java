package hello.jpa.valuetype.embedded;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    private String name;
    private int age;

    @Embedded
    private Period workPeriod;

    @Embedded
    private Address homeAddress;

    public Member(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
