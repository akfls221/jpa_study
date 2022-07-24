package hello.jpa.다형성쿼리;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "DTYPE")
public abstract class Item {

    @Id
    @GeneratedValue
    private Long id;

    private LocalDateTime createDate;

    public Item() {
    }
}
