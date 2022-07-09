package hello.jpa.cascase.delete;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class Parent {

    @Id
    @Column(name = "PARENT_ID")
    private String id;

    private String name;

    @OneToMany(mappedBy = "parent", cascade = {CascadeType.REMOVE, CascadeType.PERSIST} )
    private List<Child> childList = new ArrayList<>();

    public void addChild(Child child) {
        this.getChildList().add(child);
        if (child.getParent() != null) {
            child.setParent(this);
        }
    }

    public Parent(String id, String name) {
        this.id = id;
        this.name = name;
    }
}

