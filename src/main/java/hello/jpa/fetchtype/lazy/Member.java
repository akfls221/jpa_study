package hello.jpa.fetchtype.lazy;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
public class Member {

    @Id
    private String id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    public Member(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
