package hello.jpa.mapping.manytomanybestway;

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

    private String name;

    @OneToMany(mappedBy = "member")
    private List<Orders> ordersList = new ArrayList<>();

    public Member(String name) {
        this.name = name;
    }

    public void addOrder(Orders order) {
        if (order.getMember() != null) {
            order.setMember(this);
        }
        this.getOrdersList().add(order);
    }
}
