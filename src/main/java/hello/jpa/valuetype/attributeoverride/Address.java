package hello.jpa.valuetype.attributeoverride;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Data
public class Address {

    @Column(name = "city")
    private String city;

    private String street;

    private String zipcode;
}
