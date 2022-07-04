package hello.jpa.join.onetoonejoin;

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

    //양방향 매핑시
    @OneToOne(mappedBy = "child")
    private Parent parent;
}
