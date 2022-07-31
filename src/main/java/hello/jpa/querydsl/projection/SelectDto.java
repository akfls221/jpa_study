package hello.jpa.querydsl.projection;

import lombok.Data;

@Data
public class SelectDto {

    private int age;

    private String name;


    public SelectDto(String name, int age) {
        this.age = age;
        this.name = name;
    }
}
