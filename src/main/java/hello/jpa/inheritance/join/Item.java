package hello.jpa.inheritance.join;

import lombok.Data;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "DTYPE")
@Data
public class Item {

    @Id
    @Column(name = "ITEM_ID")
    @GeneratedValue
    private Long id;

    private String name;

    private int price;

}
