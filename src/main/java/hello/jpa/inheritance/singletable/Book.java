package hello.jpa.inheritance.singletable;

import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@DiscriminatorValue("B")
@Entity
@Data
public class Book extends Item {

    private String author;

    private int isbn;
}
