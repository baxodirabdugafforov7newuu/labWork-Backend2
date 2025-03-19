package com.example.factory;

import com.example.model.Person;

public class PersonFactory {
    public static Person createDefaultPerson() {
        return new Person("Default Name", 30, "Default Address");
    }
}
