package com.aziz.demo.Type;


import com.aziz.demo.phone.Phone;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Type {

    @Id
    @GeneratedValue
    private Integer idType;
    private String nameType;
    private String descriptionType;

    @JsonIgnore
    @OneToMany(mappedBy = "type")
    private List<Phone> phones;


}
