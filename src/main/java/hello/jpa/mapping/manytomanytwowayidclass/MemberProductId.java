package hello.jpa.mapping.manytomanytwowayidclass;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
public class MemberProductId implements Serializable {

    private Long member; //MemberProduct.member와 연결

    private Long product; //MemberProduct.Product와 연결

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberProductId that = (MemberProductId) o;
        return Objects.equals(member, that.member) && Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(member, product);
    }
}
