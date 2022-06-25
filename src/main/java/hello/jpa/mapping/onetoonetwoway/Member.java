package hello.jpa.mapping.onetoonetwoway;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.OneToOne;
import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
public class Member {

    @Id
    @GeneratedValue
    private Long id;

    private String username;

    @OneToOne
    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;

    public void setLocker(Locker locker) {
        if (this.locker != null) {
            this.locker = locker;
        }
        locker.setMember(this);
    }

    public Member(String username) {
        this.username = username;
    }
}
