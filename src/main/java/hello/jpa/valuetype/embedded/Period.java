package hello.jpa.valuetype.embedded;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.time.LocalDateTime;
import java.util.Date;

@Embeddable
@Data
public class Period {

    private LocalDateTime startDate;

    private LocalDateTime endDate;
}
