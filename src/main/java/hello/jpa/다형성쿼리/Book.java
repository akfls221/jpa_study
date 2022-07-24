package hello.jpa.다형성쿼리;

import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Data
@DiscriminatorValue("B")
public class Book extends Item {

    private String author;

    private int isbn;
}

