package com.aziz.demo.Type;


import com.aziz.demo.phone.Phone;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Date;


@Controller
@RequiredArgsConstructor
public class TypeController {

    final TypeService typeService;
    @RequestMapping("/showCreate1")
    public String showCreate() {
        return "AddType";
    }

    @RequestMapping("/saveType")
    public String saveTelephone(@ModelAttribute("Type") Type type) {
        Type saveType = typeService.createType(type);
        return "AddType";
    }
    @RequestMapping("/listeType")
    public String listeTypes(ModelMap modelMap,
                                  @RequestParam (name="page",defaultValue = "0") int page,
                                  @RequestParam (name="size", defaultValue = "2") int size)
    { Page<Type> types = typeService.getAllTypesParPage(page, size);
        modelMap.addAttribute("types", types);
        modelMap.addAttribute("pages", new int[types.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        return "ListType";
    }
    @RequestMapping("/supprimerType")
    public String supprimerType(@RequestParam("id") Integer id,
                                                                    ModelMap modelMap,
                                                                    @RequestParam (name="page",defaultValue = "0") int page,
                                                                    @RequestParam (name="size", defaultValue = "2") int size)
    {
        typeService.deleteTypeById(id);
        Page<Type> types = typeService.getAllTypesParPage(page, size);
        modelMap.addAttribute("types", types);
        modelMap.addAttribute("pages", new int[types.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        modelMap.addAttribute("size", size);
        return "listType";
    }

}
