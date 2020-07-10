package com.sunil.springes.controller;

import com.sunil.springes.model.Person;
import com.sunil.springes.service.AppService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/spring_es/v1")
public class AppController {

    AppService appService;

    public AppController(AppService appService) {
        this.appService = appService;
    }

    @PostMapping("/")
    public Person addPerson(@RequestBody Person person) throws Exception {
       return appService.addPerson(person);
    }

    @RequestMapping("/{id}")
    public Person getById(@PathVariable(name = "id") String idKey) throws  Exception {
        return appService.getByID(idKey);
    }


    @RequestMapping("/search_by_firstname_lastname")
    public List<Person> getByName(@RequestParam(name = "first_name") String firstName,
                                  @RequestParam(name = "last_name") String lastName) throws  Exception {
        return appService.getPersonByFistNameAndLastName(firstName, lastName);
    }

    @RequestMapping("/")
    public List<Person> getPersonsList() throws  Exception {
        return appService.getPersons();
    }

    @PostMapping("/search")
    public List<Person> getPersonsWithSearchAttributes(@RequestBody Map<String, String> searchMap) throws  Exception {
        return appService.getPersonBySearchAttributes(searchMap);
    }

    @PostMapping("/search_any")
    public List<Person> getPersonsAnySearchAttributes(@RequestBody Map<String, String> searchMap) throws  Exception {
        return appService.getPersonForAnySearchAttributes(searchMap);
    }

}
