package hello.jpa.inheritance.join;

import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@DiscriminatorValue("M")
@Entity
@Data
public class Movie extends Item {

    private String director;

    private String actor;
}
