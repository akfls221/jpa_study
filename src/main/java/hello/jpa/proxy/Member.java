package hello.jpa.proxy;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Data
public class Member {

    @Id
    private String id;

    private String name;

    public Member(String name) {
        this.name = name;
    }

    public Member(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
