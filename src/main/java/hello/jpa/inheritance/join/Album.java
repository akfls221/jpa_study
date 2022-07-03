package hello.jpa.inheritance.join;

import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@DiscriminatorValue("A")
@Entity
@Data
public class Album extends Item {

    private String artist;

}
