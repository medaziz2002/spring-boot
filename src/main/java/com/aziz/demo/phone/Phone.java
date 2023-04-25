package com.aziz.demo.phone;

import com.aziz.demo.Type.Type;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Phone {
    @Id
    @GeneratedValue
    private Integer idPhone;
    @NotNull(message = "Name is mondatory")
    @Size(min = 4,max = 15)
    private String phoneName;
    @NotNull(message = "price is mondatory")
    @DecimalMin(value = "10", inclusive = true)
    @DecimalMax(value = "10000", inclusive = true)
    private BigDecimal price;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent
    private Date dateOfCreation;
    @ManyToOne
    private Type type;

}
