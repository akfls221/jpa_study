package hello.jpa.inheritance.singletable;

import lombok.Data;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
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
