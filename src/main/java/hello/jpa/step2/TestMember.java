package hello.jpa.step2;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
public class TestMember {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "NAME")
    private String username;

    //연관관계 매핑
    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private TestTeam team;

    //연관관계 설정
    public void setTeam(TestTeam team) {
        this.team = team;
    }

    public TestMember(String username) {
        this.username = username;
    }
}
