package com.example.DBstrategy.Strategies.jdbc;

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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class JDBCStrategy implements Strategy {

    @Autowired
    Statement statement;

    @Override
    public void insert() {
        URL url = this.getClass().getClassLoader().getResource("people.json");
        File jsonFile = new File(url.getFile());
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            statement.executeUpdate("delete from people where id > 0");
            List<Person> people = objectMapper.readValue(jsonFile, new TypeReference<List<Person>>() {});
            for (Person person : people) {
                statement.executeUpdate("insert into people(id, first_name, last_name, address)" +
                        " values(" + person.getId() + ", '" + person.getFirst_name() +
                        "', '" + person.getLast_name() + "', '" + person.getAddress() + "')");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void insert(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            statement.executeUpdate("delete from people where id > 0");
            List<Person> people = objectMapper.readValue(json, new TypeReference<List<Person>>() {});
            for (Person person : people) {
                statement.executeUpdate("insert into people(id, first_name, last_name, address)" +
                        " values(" + person.getId() + ", '" + person.getFirst_name() +
                        "', '" + person.getLast_name() + "', '" + person.getAddress() + "')");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public String select() {
        List<Person> result = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery("select * from people");

            while (resultSet.next()) {
                Person person = new Person();
                person.setId(resultSet.getInt("id"));
                person.setFirst_name(resultSet.getString("first_name"));
                person.setLast_name(resultSet.getString("last_name"));
                person.setAddress(resultSet.getString("address"));
                result.add(person);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String resultString = null;
        try {
            resultString = objectMapper.writeValueAsString(result);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return resultString;
    }
}