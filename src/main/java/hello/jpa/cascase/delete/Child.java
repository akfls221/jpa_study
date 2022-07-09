package hello.jpa.cascase.delete;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
public class Child {

    @Id
    @Column(name = "CHILD_ID")
    private String id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    private Parent parent;

    public void setParent(Parent parent) {
        this.parent = parent;
        if (!parent.getChildList().contains(this)) {
            parent.addChild(this);
        }
    }

    public Child(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
