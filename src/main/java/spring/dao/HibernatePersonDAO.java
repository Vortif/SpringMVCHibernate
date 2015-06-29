package spring.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import spring.model.Person;

import java.util.List;

/**
 * Created by jkorzeni on 2015-06-26.
 */

@Component("personDAO")
public class HibernatePersonDAO implements PersonDAO {

    private final static Logger log = LogManager.getLogger(HibernatePersonDAO.class);
//
//    @Autowired
//    @Qualifier("hibernate4AnnotatedSessionFactory")
    private SessionFactory sessionFactory;

    @Autowired
    @Qualifier("sessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override@Transactional
    public void addPerson(Person p) {

        log.entry();

        Session session = this.sessionFactory.openSession();
        session.save(p);
        log.info("\nPerson saved successfully, Person Details="+p+"\n");
        session.close();
        log.exit();
    }

    @Override
    public void updatePerson(Person p) {

        log.entry();

        Session session = sessionFactory.openSession();
        session.saveOrUpdate(p);
        session.close();
        log.info("\nPerson updated successfully, Person Details="+p+"\n");

        log.exit();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Person> listPersons() {

        log.entry();

        Session session = sessionFactory.openSession();
        log.info("\nBefore crateQuery\n");

        List<Person> personList = session.createQuery("from Person").list();
        for(Person p : personList){
            log.info("\nPerson List: "+p+"\n");
        }

        session.close();
        log.exit();
        return personList;
    }

    @Override
    public Person getPersonById(int id) {

        log.entry();
        Session session = this.sessionFactory.openSession();
        Person p = (Person) session.load(Person.class, new Integer(id));
        log.info("\nPerson loaded successfully, Person details="+p+"\n");
        session.close();
        log.exit();

        return p;
    }

    @Override
    public void removePerson(int id) {

        log.entry();
        Session session = this.sessionFactory.openSession();
        Person p = (Person) session.load(Person.class, new Integer(id));
        if(null != p){
            session.delete(p);
        }
        session.close();
        log.info("\nPerson deleted successfully, person details="+p+"\n");
        log.exit();
    }
}
