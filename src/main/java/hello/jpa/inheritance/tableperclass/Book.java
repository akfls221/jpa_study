package hello.jpa.inheritance.tableperclass;

import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Data
public class Book extends Item {

    private String author;

    private int isbn;
}
