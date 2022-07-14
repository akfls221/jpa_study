package hello.jpa.valuetype.attributeoverride;

import lombok.Data;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
@Data
public class Period {

    private LocalDateTime startDate;

    private LocalDateTime endDate;
}
