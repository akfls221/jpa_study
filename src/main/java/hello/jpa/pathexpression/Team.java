package hello.jpa.pathexpression;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Team {

    @Id
    @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;

    private String teamName;

    @OneToMany(mappedBy = "team", fetch = FetchType.LAZY)
    private List<Member> member = new ArrayList<>();

    public Team(String teamName) {
        this.teamName = teamName;
    }

    void addMember(Member member) {
        this.getMember().add(member);
        if (member.getTeam() != this) {
            member.setTeam(this);
        }
    }


}
