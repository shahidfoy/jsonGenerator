package com.jsongenerator.factory.domain.controllers;

import com.jsongenerator.factory.domain.models.Person;
import com.jsongenerator.factory.domain.services.PersonService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/people")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping(value = "/person/{id}")
    public ResponseEntity<Person> findPost(@PathVariable("id") String id) {
        return ResponseEntity.ok(this.personService.findPostById(id));
    }

    @GetMapping
    public ResponseEntity<List<Person>> findAllPosts() {
        try {
            Runtime.getRuntime().exec("mongoexport --host localhost --port 27017 --db json-factory-data --collection people --out outputData/People.json");
        } catch(IOException IOe) {
            System.out.println("Error: " + IOe);
        }
        return ResponseEntity.ok(this.personService.getAllPosts());
    }

    @PostMapping
    public ResponseEntity<Person> newPost(Person post) {
         Person res =  this.personService.insertNewPost(post);

        // ResponseEntity<Person> res =  new ResponseEntity<>(this.personService.insertNewPost(post), HttpStatus.CREATED);
        try {
            System.out.println(res.getId());
            Runtime.getRuntime().exec(String.format("mongoexport --host localhost --port 27017 --db json-factory-data --collection people --query \"{ _id : \'%s\' }\" --out outputData/person.json", res.getId()));
        } catch(IOException IOe) {
            System.out.println("Error: " + IOe);
        }

        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removePost(@PathVariable("id") String id) {
        this.personService.deletePost(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> update(@PathVariable("id") String id, Person post) {
        try {
            Runtime.getRuntime().exec("mongoexport --host localhost --port 27017 --db json-factory-data --collection people --out outputData/person.json");
        } catch(IOException IOe) {
            System.out.println("Error: " + IOe);
        }
        return new ResponseEntity<>(this.personService.updatePost(id, post), HttpStatus.OK);
    }
}
