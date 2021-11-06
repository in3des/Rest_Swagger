package com.example.springboot_io_313.controllers;

import com.example.springboot_io_313.entity.Person;
import com.example.springboot_io_313.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;


@Controller
@RequestMapping("/")
public class PeopleController {

    private final PeopleService peopleService;

    @Autowired
    public PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @GetMapping("/admin")
    public String showIndexPage(Model model, Authentication authentication) {
        model.addAttribute("people", peopleService.index());
        model.addAttribute("personA", peopleService.findPersonByEmail(((Person) authentication.getPrincipal()).getEmail()));
        model.addAttribute("person2", new Person());
        return "people/index";
    }




    @GetMapping("/user")
    public String showOneUserPage(Model model, Authentication authentication) {
        model.addAttribute("person", peopleService.findPersonByEmail(((Person) authentication.getPrincipal()).getEmail()));
        return "people/show";
    }


    @PostMapping("/admin")
    public String createPerson(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "people/index";
        }

        peopleService.save(person);
        return "redirect:/admin";
    }




    @PostMapping("admin/{id}")
    public String UpdatePerson(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult,
                               @PathVariable("id") Long id) {

//        System.out.println("------------------------------");
//
//        System.out.println(person.getRoles());
//
//        System.out.println("user_id=" + id + " if user_role --> " + person.getRoles().toString().contains("ROLE_USER"));
//        System.out.println("user_id=" + id + " if admin_role --> " + person.getRoles().toString().contains("ROLE_ADMIN"));
//
//        System.out.println("------------------------------");
//
//        System.out.println("admin >>> " + person.checkAdmin());
//        System.out.println("user >>> " + person.checkUser());
//
//        System.out.println("------------------------------");



        if (bindingResult.hasErrors()) {
            return "redirect:/admin/{id}";
        }

        peopleService.update(person, id);
        return "redirect:/admin";
    }


    @PostMapping("/admin/delete/{id}")
    public String deletePerson(@PathVariable("id") Long id, Model model) {
        peopleService.delete(id);
        return "redirect:/admin";
    }


    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

}







