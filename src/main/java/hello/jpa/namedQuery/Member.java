package hello.jpa.namedQuery;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NoArgsConstructor
@Data
@NamedQuery(name = "Member.selectByName",
        query = "select m.name from Member m where m.name=:userName"
)
public class Member {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private int age;


    public Member(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
