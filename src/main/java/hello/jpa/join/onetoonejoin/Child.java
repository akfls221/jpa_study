package hello.jpa.join.onetoonejoin;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Data
public class Child {

    @Id
    @GeneratedValue
    @Column(name = "CHID_ID")
    private Long id;

    private String name;

    public Child(String name) {
        this.name = name;
    }
}
