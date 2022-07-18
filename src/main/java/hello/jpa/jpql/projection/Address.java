package hello.jpa.jpql.projection;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
@Data
public class Address {

    private String city;

    private String street;
}
