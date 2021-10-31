package com.luxoft.querygenerator.sql;



import com.luxoft.querygenerator.entity.Person;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SqlQueryGeneratorTest {

    SqlQueryGenerator sqlQueryGenerator = new SqlQueryGenerator();

    @Test
    public void testFindAllReturnValidQuery() {

        String expected = "SELECT id, person_name, salary FROM persons;";

        String actual = sqlQueryGenerator.findAll(Person.class);

        assertEquals(expected, actual);
    }

    @Test
    void findById() {
        String expected = "SELECT id, person_name, salary FROM persons WHERE id = '1';";

        String actual = sqlQueryGenerator.findById(Person.class, 1);

        assertEquals(expected, actual);

    }

    @Test
    void insert() throws IllegalAccessException {
        Person person = new Person(1,"Alex", 10000);

        String expected = "INSERT INTO persons (name, salary) VALUES ('Alex', '100000');";

        String actual = sqlQueryGenerator.insert(person);

        assertEquals(expected, actual);
    }



    @Test
    void remove() {
        Person person = new Person(10,"Alex", 10000);

        String expected = "DELETE * FROM persons WHERE id = '10';";

//        String actual = sqlQueryGenerator.remove(person);

//        assertEquals(expected, actual);


    }

    @Test
    void update() {
        Person person = new Person(10,"Alex", 10000);

        String expected = "DELETE * FROM persons WHERE id = '10';";

        String actual = sqlQueryGenerator.update(person);

        assertEquals(expected, actual);
    }
}

/*
* public class Person {
    private int id;
    private String name;
    private double salary;
}

* */