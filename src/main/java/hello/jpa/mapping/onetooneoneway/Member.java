package hello.jpa.mapping.onetooneoneway;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.persistence.OneToOne;

@Entity
@NoArgsConstructor
@Data
public class Member {

    @Id
    @GeneratedValue
    private Long id;

    private String username;

    @OneToOne
    @JoinColumn(name = "LPCKER_ID")
    private Locker locker;

    public Member(String username) {
        this.username = username;
    }
}
