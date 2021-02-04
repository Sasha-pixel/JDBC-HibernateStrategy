package com.example.DBstrategy.Strategies;

import org.springframework.stereotype.Component;

public interface Strategy {
    public void insert();
    public void insert(String json);
    public String select();
}
