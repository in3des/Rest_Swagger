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


@RestController
@RequestMapping("/")
public class PeopleRestController {

    private final PeopleService peopleService;

    @Autowired
    public PeopleRestController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }


//    =================================== REST API

    @GetMapping("api/users")
    public List<Person> apiGetPeopleList() {
        return peopleService.index();
    }

    @GetMapping("api/findlogged")
    public Person apiFindLoggedUser(Authentication authentication) {
        return peopleService.findPersonByEmail(((Person) authentication.getPrincipal()).getEmail());
    }

    @GetMapping("api/newperson")
    public Person apiNewPerson() {
        return new Person();
    }

    @GetMapping("/api/users/{id}")
    public ResponseEntity<Person> get(@PathVariable Long id) {
        try {
            Person person = peopleService.show(id);
            return new ResponseEntity<>(person, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/api/users")
    public void apiCreatePerson(@RequestBody Person person) {
        peopleService.save(person);
    }

    @PutMapping("/api/users/{id}")
    public ResponseEntity<?> apiUpdatePerson(@RequestBody Person person, @PathVariable Long id) {
        try {
            peopleService.update(person, id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/api/users")
    public ResponseEntity<?> apiUpdatePerson(@RequestBody Person person) {
        try {
            peopleService.updateV2(person);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/api/users/{id}")
//    @ResponseBody
    public void apiDeletePerson(@PathVariable Long id) {
        peopleService.delete(id);
    }


//    =================================== REST API

}


