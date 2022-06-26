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
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(
            name = "MEMBER_PRODUCT",
            joinColumns = @JoinColumn(name = "MEMBER_ID"),
            inverseJoinColumns = @JoinColumn(name = "PRODUCT_ID")

    )
    private List<Product> product = new ArrayList<>();

    //연관관계 편의 메서드
    public void addProduct(Product product) {
        this.product.add(product);
        product.getMemberList().add(this);
    }

    public Member(String name) {
        this.name = name;
    }
}
