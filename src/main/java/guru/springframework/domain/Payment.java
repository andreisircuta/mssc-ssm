package guru.springframework.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Payment {
    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)    //create a varchar in with the value = name of the enum
    private PaymentState state;

    private BigDecimal amount;
}
