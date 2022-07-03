package hello.jpa.manykey.manykeyforIdclass;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@IdClass(ParentId.class)
@Data
@NoArgsConstructor
public class Parent {

    @Id
    @GeneratedValue
    @Column(name = "PARENTID1")
    private Long parentId1;

    @Id
    @GeneratedValue
    @Column(name = "PARENTID2")
    private Long parentId2;

    private String name;

    public Parent(String name) {
        this.name = name;
    }
}
