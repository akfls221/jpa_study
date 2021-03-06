package hello.jpa.manykey.manykeyforembeddedid;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
@NoArgsConstructor
public class ParentId implements Serializable {

    @Column(name = "Parent_ID1")
    private Long parentId1;

    @Column(name = "Parent_ID2")
    private Long parentId2;

    public ParentId(Long parentId1, Long parentId2) {
        this.parentId1 = parentId1;
        this.parentId2 = parentId2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParentId parentId = (ParentId) o;
        return Objects.equals(parentId1, parentId.parentId1) && Objects.equals(parentId2, parentId.parentId2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parentId1, parentId2);
    }
}
