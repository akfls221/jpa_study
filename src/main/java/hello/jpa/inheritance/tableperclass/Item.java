package hello.jpa.inheritance.tableperclass;

import lombok.Data;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Data
public class Item {

    @Id
    @Column(name = "ITEM_ID")
    @GeneratedValue
    private Long id;

    private String name;

    private int price;

}
