package hello.jpa.inheritance.mappedsuperclass;

import lombok.Data;

import javax.persistence.*;

@MappedSuperclass
@Data
public class BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

}
