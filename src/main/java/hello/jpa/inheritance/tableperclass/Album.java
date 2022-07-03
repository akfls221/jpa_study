package hello.jpa.inheritance.tableperclass;

import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Data
public class Album extends Item {

    private String artist;

}
