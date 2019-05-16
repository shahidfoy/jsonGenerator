package com.jsongenerator.factory.domain.services;

import com.jsongenerator.factory.domain.models.Person;
import com.jsongenerator.factory.domain.repsitories.PersonRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person insertNewPost(Person post) {
        Person newpost = new Person();
        newpost.setId(UUID.randomUUID().toString());
        newpost.setFirstName(post.getFirstName());
        newpost.setLastName(post.getLastName());
        newpost.setAge(post.getAge());
        return this.personRepository.save(newpost);
    }

    public Person updatePost(String id, Person post) {
        Optional<Person> postOptional = personRepository.findById(id);

        if(!postOptional.isPresent()) {
            throw new RuntimeException("Person not found");
        }
        Person updatedpost = postOptional.get();

        updatedpost.setFirstName(post.getFirstName());
        updatedpost.setLastName(post.getLastName());
        updatedpost.setAge(post.getAge());

        return this.personRepository.save(updatedpost);
    }

    public List<Person> getAllPosts() {
        List<Person> people;
        people = personRepository.findAll();
        return people;
    }

    public Person findPostById(String id) {
        Optional<Person> postOptional = personRepository.findById(id);

        if(!postOptional.isPresent()) {
            throw new RuntimeException("Post not found");
        }

        return postOptional.get();
    }

    public Person savePost(Person post) {
        return personRepository.save(post);
    }

    public void deletePost(String id) {
        personRepository.deleteById(id);
    }
}
