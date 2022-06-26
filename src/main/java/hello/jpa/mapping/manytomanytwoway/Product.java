package hello.jpa.mapping.manytomanytwoway;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ManyToMany;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class Product {

    @Id
    @GeneratedValue
    @Column(name = "PRODUCT_ID")
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "product")
    private List<Member> memberList = new ArrayList<>();

    public Product(String name) {
        this.name = name;
    }
}
