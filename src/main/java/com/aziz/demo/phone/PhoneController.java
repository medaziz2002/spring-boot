package com.aziz.demo.phone;



import com.aziz.demo.Type.Type;
import com.aziz.demo.Type.TypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class PhoneController {

    final PhoneService phoneService;
    final PhoneRepository phoneRepository;
   final TypeService typeService;

    @RequestMapping("/showCreate")
    public String showCreate(ModelMap modelMap) {
        modelMap.addAttribute("phone", new Phone());
        modelMap.addAttribute("mode", "new");
        List<Type> t =typeService.findAll();
        modelMap.addAttribute("types",t);
        return "formPhone";
    }

    @PostMapping("/saveTelephone")

    public String saveTelephone(@Valid Phone phone,ModelMap modelMap,
                                BindingResult bindingResult){
        List<Type> t =typeService.findAll();
        modelMap.addAttribute("types",t);
        if (bindingResult.hasErrors()) return "FormPhone";
        phoneService.savePhone(phone);
        return "FormPhone";
    }

    @GetMapping ("/listeTelephone")
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

    @DeleteMapping("/supprimerPhone")

    public String supprimerPhone(@RequestParam("id") Integer id,
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
        List<Type> t =typeService.findAll();
        modelMap.addAttribute("types",t);
        modelMap.addAttribute("mode", "edit");
        return "formPhone";
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

/*    final PhoneService phoneService;
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


    @GetMapping("/")
    public String home()
    {
        return "redirect:/listeTelephone";
    }
    @GetMapping("/index")
    public String index(Model model,
                        @RequestParam(name = "page",defaultValue = "0") int page,
                        @RequestParam(name = "size",defaultValue = "5") int size,
                        @RequestParam(name = "keyword",defaultValue = "") String kw
    ){
        System.out.println("dans le controller");
        Page<Phone> pagePhones = phoneRepository.findByPhoneNameContains(kw, PageRequest.of(page,size));
        System.out.println("apres appel la fonction");
        System.out.println("rest"+pagePhones.getContent().isEmpty());
        model.addAttribute("phones",pagePhones.getContent());
        model.addAttribute("pages",new int[pagePhones.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",kw);
        return "listeTelephones";
    }
}
