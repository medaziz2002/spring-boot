package com.aziz.demo.phone;

import com.aziz.demo.Type.Type;
import jakarta.persistence.*;
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
    private String phoneName;
    private BigDecimal price;

    private Date dateOfCreation;
    @ManyToOne
    private Type type;

}
