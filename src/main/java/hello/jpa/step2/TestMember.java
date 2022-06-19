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

    //연관관계 설정 + 연관관계 편의 메서드
    public void setTeam(TestTeam team) {
        //기존의 Team이 있다면, Team 객체의 Members 리스트에서도 멤버 삭제(기존 팀과 관계 제거)
        if (this.team != null) {
            this.team.getMembers().remove(this);
        }
        this.team = team;
        team.getMembers().add(this);
    }

    public TestMember(String username) {
        this.username = username;
    }
}
