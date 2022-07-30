package hello.jpa.querydsl.join;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class OtherMember {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    public OtherMember(String name) {
        this.name = name;
    }
}
