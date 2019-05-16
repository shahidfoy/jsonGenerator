package com.jsongenerator.factory.domain.repsitories;

import com.jsongenerator.factory.domain.models.Person;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PersonRepository extends MongoRepository<Person, String> {

}
