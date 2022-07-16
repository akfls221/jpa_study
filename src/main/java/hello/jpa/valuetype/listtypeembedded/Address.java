package hello.jpa.valuetype.listtypeembedded;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
@Data
public class Address {

    @Column(name = "city")
    private String city;

    private String street;

    public Address(String city, String street) {
        this.city = city;
        this.street = street;
    }
}
