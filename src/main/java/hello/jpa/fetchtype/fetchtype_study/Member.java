package hello.jpa.fetchtype.fetchtype_study;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class Member {

    @Id
    @Column(name = "MEMBER_ID")
    private String id;

    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    private Team team;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private List<Order> orderList = new ArrayList<>();

    public Member(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addOrder(Order order) {
        this.getOrderList().add(order);
    }
}
