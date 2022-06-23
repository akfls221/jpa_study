package hello.jpa.mapping.onetomanyoneway;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class Team {

    @Id
    @GeneratedValue
    @Column(name = "TEAM_ID")
    private long id;

    private String name;

    @OneToMany
    @JoinColumn(name = "TEAM_ID")
    private List<Member> members = new ArrayList<>();

    public Team(String name) {
        this.name = name;
    }

}
