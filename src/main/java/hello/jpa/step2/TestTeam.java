package hello.jpa.step2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class TestTeam {

    @Id
    @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;

    private String teamName;

    @OneToMany(mappedBy = "team")
    private List<TestMember> members = new ArrayList<>();

    public TestTeam(String teamName) {
        this.teamName = teamName;
    }
}
