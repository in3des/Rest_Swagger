package com.example.springboot_io_313.dao;

import com.example.springboot_io_313.entity.Person;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class PersonDAOImpl implements PersonDAO {

    @PersistenceContext // or even @Autowired
    private EntityManager em;

    @Override
    public List<Person> index() {
        return em.createQuery("SELECT person FROM Person person", Person.class).getResultList();
    }

    @Override
    public Person show(final Long id) {
        return em.find(Person.class, id);
    }

    @Override
    public void save(Person person) {
        em.persist(person);
    }

    @Override
    public void update(Person updatedPerson, Long id) {
        em.merge(updatedPerson);
    }

    @Override
    public void updateV2(Person updatedPerson) {
        em.merge(updatedPerson);
    }

    @Override
    public void delete(Long id) {
        Person person = em.find(Person.class, id);
        em.remove(person);
    }

    @Override
    public Person findPersonByEmail(String email) {
        return em.createQuery("FROM Person p WHERE p.email =:email", Person.class).setParameter("email", email).getSingleResult();
    }

    @Override
    public Person findPersonByName(String name) {
        return em.createQuery("FROM Person p WHERE p.name =:name", Person.class).setParameter("name", name).getSingleResult();
    }


}

