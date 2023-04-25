package com.aziz.demo.phone;
import com.aziz.demo.Type.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.math.BigDecimal;
import java.util.List;


@RepositoryRestResource(path = "Rest")
public interface PhoneRepository extends JpaRepository<Phone,Integer> {



    Page<Phone> findByPhoneNameContains(String kw, Pageable pageable);
    List<Phone> findByPhoneName(String name);
    List<Phone> findByPhoneNameContains(String name);

    @Query("select p from Phone p where p.phoneName like %?1 and p.price > ?2")
    List<Phone> findByNamePrice (String phoneName, BigDecimal price);

    @Query("select p from Phone p where p.type = ?1")
    List<Phone> findByType (Type type);


    List<Phone> findByTypeIdType(Integer id);

    List<Phone> findByOrderByPhoneNameAsc();

    @Query("select p from Phone p order by p.phoneName ASC, p.price DESC")
    List<Phone> trierPhonesNamePrice ();

}
