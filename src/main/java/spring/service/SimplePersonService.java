package spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import spring.dao.PersonDAO;
import spring.model.Person;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by jkorzeni on 2015-06-26.
 */

@Service("personService")
public class SimplePersonService implements PersonService {

    @Autowired
    @Qualifier("personDAO")
    private PersonDAO personDAO;

    public void setPersonDAO(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Override
    @Transactional
    public void addPerson(Person p) {
        personDAO.addPerson(p);
    }

    @Override
    @Transactional
    public void updatePerson(Person p) {
        personDAO.updatePerson(p);
    }

    @Override
    @Transactional
    public List<Person> listPersons() {
        return personDAO.listPersons();
    }

    @Override
    @Transactional
    public Person getPersonById(int id) {
        return personDAO.getPersonById(id);
    }

    @Override
    @Transactional
    public void removePerson(int id) {
        personDAO.removePerson(id);
    }
}
