package hello.jpa.mapping.onetomanyoneway;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
public class Member {

    @Id
    @GeneratedValue
    private long id;

    private String username;

    public Member(String username) {
        this.username = username;
    }
}
