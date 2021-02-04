package com.example.DBstrategy;

import com.example.DBstrategy.Strategies.Strategy;
import com.example.DBstrategy.Strategies.jdbc.JDBCStrategy;
import com.example.DBstrategy.Strategies.jpa.JPAStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;

@org.springframework.stereotype.Service
public class Service {

    @Autowired
    @Qualifier("JDBCStrategy")
    Strategy current;

    @Autowired
    private ApplicationContext context;

    public void insertFile() {
        current.insert();
    }

    public void insertStr(String json) {
        current.insert(json);
    }

    public String selectAll() {
        return current.select();
    }

    public String changeStrategy(String name) {
        if (name.equals("jdbc")) {
            current = context.getBean(JDBCStrategy.class);
            return "You have chosen JDBC strategy";
        }
        else if (name.equals("jpa")) {
            current = context.getBean(JPAStrategy.class);
            return "You have chosen JPA strategy";
        }
        else return "enter \"jdbc\" or \"jpa\"";
    }
}
