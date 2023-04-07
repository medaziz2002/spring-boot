package com.aziz.demo.phone;



import com.aziz.demo.Type.Type;
import com.aziz.demo.Type.TypeService;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class PhoneController {

    final PhoneService phoneService;
   final TypeService typeService;

    @RequestMapping("/showCreate")
    public String showCreate() {
        return "AddPhone";
    }
    @RequestMapping("/saveTelephone")
    public String saveTelephone(@ModelAttribute("phone") Phone phone,
                              @RequestParam("date") String date,
                              ModelMap modelMap) throws java.text.ParseException {
        //conversion de la date
        System.out.println("ici");
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateCreation = dateformat.parse(String.valueOf(date));
        System.out.println(dateCreation);
        phone.setDateOfCreation(dateCreation);
        Phone savePhone = phoneService.savePhone(phone);
        String msg = "phone saved with  Id " + savePhone.getIdPhone();
        modelMap.addAttribute("msg", msg);
        return "AddPhone";
    }

    @RequestMapping("/listeTelephone")
    public String listeTelephones(ModelMap modelMap,
                                  @RequestParam (name="page",defaultValue = "0") int page,
                                  @RequestParam (name="size", defaultValue = "2") int size)
    { Page<Phone> phones = phoneService.getAllPhonesParPage(page, size);
        List<Type> types=  typeService.findAll();
        modelMap.addAttribute("types", types);
        modelMap.addAttribute("phones", phones);
        modelMap.addAttribute("pages", new int[phones.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        return "listeTelephones";
    }

    @RequestMapping("/supprimerPhone") public String supprimerPhone(@RequestParam("id") Integer id,
                                                                        ModelMap modelMap,
                                                                        @RequestParam (name="page",defaultValue = "0") int page,
                                                                        @RequestParam (name="size", defaultValue = "2") int size)
    {
        phoneService.deletePhoneById(id);
        Page<Phone> phones = phoneService.getAllPhonesParPage(page, size);
        modelMap.addAttribute("phones", phones);
        modelMap.addAttribute("pages", new int[phones.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        modelMap.addAttribute("size", size);
        return "listeTelephones";
    }
    @RequestMapping("/modifierPhone")
    public String editerPhone(@RequestParam("id") Integer id,ModelMap modelMap)
    {
        Phone p= phoneService.getPhone(id);
        modelMap.addAttribute("phone", p);
        return "editerProduit";
    }
    @RequestMapping("/updatePhone")
    public String updatePhone(@ModelAttribute("phone") Phone phone,
                                @RequestParam("date") String date,
                                ModelMap modelMap) throws java.text.ParseException {
        //conversion de la date SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateCreation = dateformat.parse(String.valueOf(date));
        phone.setDateOfCreation(dateCreation);
        phoneService.updatePhone(phone);
        List<Phone> phones = phoneService.getAllPhones();
        modelMap.addAttribute("phones", phones);
        return "listeTelephones";
    }

/*
    final PhoneService phoneService;
    @GetMapping
    public List<Phone> getAllPhones() {
        return phoneService.getAllPhones();
    }

    @GetMapping(value="/{id}")
    public Phone getPhoneById(@PathVariable("id") Integer id) { return phoneService.getPhone(id);
    }
    @PutMapping
    public Phone updatePhone(@RequestBody Phone phone) {

        return phoneService.updatePhone(phone);
    }
    @DeleteMapping(value="/{id}")
    public void deletePhone(@PathVariable("id") Integer id)
    {
        phoneService.deletePhoneById(id);
    }
    @GetMapping(value="/phonetype/{idType}")
    public List<Phone> getPhonesByTypeId(@PathVariable("idType") Integer idType) {
        return phoneService.findByTypeIdCat(idType);
    }*/
}
