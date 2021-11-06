package com.example.springboot_io_313.dao;

import com.example.springboot_io_313.entity.Person;

import java.util.List;

public interface PersonDAO{

    public List<Person> index();

    public Person show(final Long id);

    public void save(Person person);

    public void update(Person updatedPerson, Long id);

    public void updateV2(Person updatedPerson);

    public void delete(Long id);

    public Person findPersonByEmail(String email);

    public Person findPersonByName(String email);




}

