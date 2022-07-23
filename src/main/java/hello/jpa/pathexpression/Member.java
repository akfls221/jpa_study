package hello.jpa.pathexpression;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    private String name; //상태필드
    private int age; //상태필드

    @ManyToOne
    private Team team; //연관필드(단일값 연관필드)

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>(); //연관필드(컬렉션 값 연관 필드)

    void setTeam(Team team) {
        this.team = team;
        if (!team.getMember().contains(this)) {
            team.getMember().add(this);
        }
    }

    void addOrder(Order order) {
        this.getOrders().add(order);
        order.setMember(this);
    }

    public Member(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
