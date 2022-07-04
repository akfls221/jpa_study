package hello.jpa.join.ontetomanyoneway;

import hello.jpa.join.onetoonejoin.Parent;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
