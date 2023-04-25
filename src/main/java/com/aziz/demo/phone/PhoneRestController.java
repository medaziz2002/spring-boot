package com.aziz.demo.phone;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
@RequiredArgsConstructor
public class PhoneRestController {

    final PhoneService phoneService;

    @GetMapping
    public List<Phone> getAllPhones() {
        return phoneService.getAllPhones();
    }

    @GetMapping("/{phone-id}")
    public Phone getById(
            @PathVariable("phone-id") Integer id
    )
    {
        return phoneService.getPhone(id);
    }



    @PostMapping
    public Integer addPhone(
            @RequestBody Phone phone
    )
    {
        return phoneService.savePhone(phone).getIdPhone();
    }

    @PutMapping("/{phone-id}")
    public Integer updatePhone(
            @RequestBody Phone phone
    )
    {
        return phoneService.updatePhone(phone).getIdPhone();
    }


    @DeleteMapping("/{phone-id}")
    public void deletePhone(
            @PathVariable("phone-id")  Integer id
    )
    {
         phoneService.deletePhoneById(id);
    }

    @GetMapping("/phonescat/{idType}")
    public List<Phone> getPhonesByTypeId(@PathVariable("idType") Integer id)
    {
      return   phoneService.findByTypeIdType(id);
    }


}
