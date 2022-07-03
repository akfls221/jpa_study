package hello.jpa.inheritance.mappedsuperclass;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "SELLER_ID"))
public class Seller extends BaseEntity {

    private String shopNumber;
}
