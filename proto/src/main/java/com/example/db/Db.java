package com.example.db;

import com.example.greeting.Greeting;

import java.util.ArrayList;
import java.util.List;

public class Db {

    public static List<Greeting> getNames() {
        List<Greeting> greetings = new ArrayList<>();
        greetings.add(Greeting.newBuilder().setName("Sasha").setMessage("Hello Sasha").build());
        greetings.add(Greeting.newBuilder().setName("Alex").setMessage("Hello Alex").build());
        greetings.add(Greeting.newBuilder().setName("Maria").setMessage("Hello Maria").build());
        greetings.add(Greeting.newBuilder().setName("Roman").setMessage("Hello Roman").build());
        return greetings;
    }
}
