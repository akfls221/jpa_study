package hello.jpa.fetchtype.fetchtype_study;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
@Table(name = "ORDERS")
public class Order {

    @Id
    @Column(name = "ORDER_ID")
    private String id;

    private int orderCount;

    public Order(String id) {
        this.id = id;
    }
}
