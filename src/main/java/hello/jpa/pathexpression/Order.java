package hello.jpa.pathexpression;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
@Table(name = "ORDERS")
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;

    private String product;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;
}
