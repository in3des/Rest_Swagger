package com.example.springboot_io_313.controllers;

import com.example.springboot_io_313.entity.Person;
import com.example.springboot_io_313.service.PeopleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;


@Tag(name = "Person", description = "The Person API")
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
    @Operation(summary = "Gets all persons", tags = "Person")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Found the users",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Person.class)))
                    })
    })
    public List<Person> apiGetPeopleList() {
        return peopleService.index();
    }


    @GetMapping("api/findlogged")
    @Operation(summary = "Gets all persons", tags = "Person", description = "jdfghsdjgfjdsfj")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешное получение списка приемок товара"),
            @ApiResponse(responseCode = "404", description = "Данный контроллер не найден"),
            @ApiResponse(responseCode = "403", description = "Операция запрещена"),
            @ApiResponse(responseCode = "401", description = "Нет доступа к данной операции")}
    )
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


