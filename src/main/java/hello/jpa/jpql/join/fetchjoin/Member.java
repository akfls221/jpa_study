package hello.jpa.jpql.join.fetchjoin;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    public Member(String name) {
        this.name = name;
    }

    void setTeam(Team team) {
        this.team = team;
        if (!team.getMember().contains(this)) {
            team.getMember().add(this);
        }
    }
}
