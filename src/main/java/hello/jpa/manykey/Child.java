package hello.jpa.manykey;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Child {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "PARENT_ID1", referencedColumnName = "PARENTID1"),
            @JoinColumn(name = "PARENT_ID2", referencedColumnName = "PARENTID2")
    })
    private Parent parent;
}
