package hello.jpa.querydsl.join;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Data
public class OrderMember {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    private int age;

    public OrderMember(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
