package hello.jpa.fetchtype.fetchtype_study;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Data
public class Team {

    @Id
    private String id;

    @Column(name = "TEAM_NAME")
    private String name;

    public Team(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
