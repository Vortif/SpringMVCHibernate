package spring.dao;

import spring.model.Person;

import java.util.List;

/**
 * Created by jkorzeni on 2015-06-26.
 */
public interface PersonDAO {

    public void addPerson(Person p);
    public void updatePerson(Person p);
    public List<Person> listPersons();
    public Person getPersonById(int id);
    public void removePerson(int id);
}