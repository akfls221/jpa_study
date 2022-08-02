package hello.jpa.querydsl.projection;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//@Data
@NoArgsConstructor
//@Setter
@ToString
public class SelectDto {

    private int age;

    private String name;


    public SelectDto(String name, int age) {
        this.age = age;
        this.name = name;
    }
}
