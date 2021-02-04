package com.example.DBstrategy.Strategies.jpa;

import com.example.DBstrategy.entity.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonJpaRepository extends CrudRepository<Person, Integer> {

}
