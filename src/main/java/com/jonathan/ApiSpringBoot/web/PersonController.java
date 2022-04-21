package com.jonathan.ApiSpringBoot.web;

import com.jonathan.ApiSpringBoot.Service.PersonService;
import com.jonathan.ApiSpringBoot.domain.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;

@Controller
@Slf4j
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/")
    public String home(Model model, @AuthenticationPrincipal User user) {
        var persons = personService.getPerson();
        log.info("ejecutando el controlador Spring MVC");
        log.info("Este es el usuario que hizo login: " + user);
        model.addAttribute("persons", persons);
        var amountTotal = 0D;
        for (var p: persons) {
            amountTotal += p.getAmount();
        }
        model.addAttribute("amountTotal", amountTotal);
        model.addAttribute("totalClients", persons.size());
        return "index";
    }

    @GetMapping("/add")
    public String addPerson(Person person) {
        return "mod";
    }

    @PostMapping("/save")
    public String savePerson(@Valid Person person, Errors errors) {
        if (errors.hasErrors()) {
            return "mod";
        }
        personService.savePerson(person);
        return "redirect:/"; //Redirecciona a la pagina de inicio (index.html)
    }

    @GetMapping("/edit/{idPerson}") //PathVariable
    public String editPerson(Person person, Model model) {
        person = personService.findPerson(person);
        model.addAttribute("person", person);
        return "mod";
    }

    @GetMapping("/delete") //QueryParameter
    public String deletePerson(Person person) {
        personService.deletePerson(person);
        return "redirect:/";
    }
}
