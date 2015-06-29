package spring.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import spring.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import spring.service.PersonService;

/**
 * Created by jkorzeni on 2015-06-26.
 */

@Controller
public class PersonController {

    private final static Logger logger = LogManager.getLogger(PersonController.class);
    private PersonService personService;

    @Autowired(required = true)
    @Qualifier("personService")
    public void setPersonService(PersonService personService) {

        this.personService = personService;
    }

    @RequestMapping(value = "/persons", method = RequestMethod.GET)
    public String listPersons(Model model){
        logger.entry();
        logger.info("\nEntering listPersons\n");
        model.addAttribute("person", new Person());
        model.addAttribute("listPersons", personService.listPersons());

        logger.exit();
        return "person";
    }

    @RequestMapping(value = "/person/add", method = RequestMethod.POST)
    public String addPerson(@ModelAttribute("person") Person p){

        logger.entry();
        logger.info("\nEntering addPerson\n");

        if(p.getId() == 0){
            personService.addPerson(p);
        }else{
            personService.updatePerson(p);
        }
        logger.info("\nPerson added: " + p+"\n");
        logger.info("\nBefore redirect:/persons\n");
        logger.exit();
        return "redirect:/persons";


    }

    @RequestMapping("/remove/{id}")
    public String removePerson(@PathVariable("id") int id){

        personService.removePerson(id);
        return "redirect:persons";
    }

    @RequestMapping("/edit/{id}")
    public String editPerson(@PathVariable("id") int id, Model model){

        model.addAttribute("person", personService.getPersonById(id));
        model.addAttribute("listPersons", personService.listPersons());

        return "person";
    }


}
