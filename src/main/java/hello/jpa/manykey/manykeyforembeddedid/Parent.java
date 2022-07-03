package hello.jpa.manykey.manykeyforembeddedid;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
public class Parent {

    @EmbeddedId
    private ParentId id;

    private String name;

    public Parent(String name) {
        this.name = name;
    }
}
