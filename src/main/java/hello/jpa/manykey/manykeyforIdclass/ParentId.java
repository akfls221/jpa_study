package hello.jpa.manykey.manykeyforIdclass;

import java.io.Serializable;
import java.util.Objects;


public class ParentId implements Serializable {

    private Long parentId1;

    private Long parentId2;

    public ParentId() {
    }

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
