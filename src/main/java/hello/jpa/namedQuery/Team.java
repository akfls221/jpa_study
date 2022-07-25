package hello.jpa.namedQuery;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@NamedQueries({
        @NamedQuery(name = "Team.findByName",
                query = "select t from Team t where t.teamName=:teamName"
        ),
        @NamedQuery(name = "Team.findByLeader",
                query = "select t from Team t where t.leaderName=:leaderName"
        )
})
public class Team {

    @Id
    @GeneratedValue
    private Long id;

    private String teamName;

    private String leaderName;

    public Team(String teamName, String leaderName) {
        this.teamName = teamName;
        this.leaderName = leaderName;
    }
}
