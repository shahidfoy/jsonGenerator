package com.jsongenerator.factory.domain.controllers;

import com.jsongenerator.factory.domain.models.Person;
import com.jsongenerator.factory.domain.services.PersonService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return ResponseEntity.ok(this.personService.getAllPosts());
    }

    @PostMapping
    public ResponseEntity<Person> newPost(Person post) {
        return new ResponseEntity<>(this.personService.insertNewPost(post), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removePost(@PathVariable("id") String id) {
        this.personService.deletePost(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> update(@PathVariable("id") String id, Person post) {
        return new ResponseEntity<>(this.personService.updatePost(id, post), HttpStatus.OK);
    }
}
