package com.example.DBstrategy.Strategies.jpa;

import com.example.DBstrategy.Strategies.Strategy;
import com.example.DBstrategy.entity.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

@Component
public class JPAStrategy implements Strategy {

    @Autowired
    PersonJpaRepository personJpaRepository;

    @Override
    public void insert() {
        URL url = this.getClass().getClassLoader().getResource("people.json");
        File jsonFile = new File(url.getFile());
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<Person> people = objectMapper.readValue(jsonFile, new TypeReference<List<Person>>() {});
            personJpaRepository.saveAll(people);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insert(String json)  {
        personJpaRepository.deleteAll();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            personJpaRepository.saveAll(objectMapper.readValue(json, new TypeReference<List<Person>>() {}));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String select() {
        Iterable<Person> people = personJpaRepository.findAll();
        ObjectMapper objectMapper = new ObjectMapper();
        String resultString = null;
        try {
            resultString = objectMapper.writeValueAsString(people);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return resultString;
    }
}
