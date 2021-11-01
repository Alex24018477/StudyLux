package com.luxoft.querygenerator.entity;

import com.luxoft.querygenerator.domain.Entity;
import com.luxoft.querygenerator.domain.Id;

import java.lang.reflect.Field;

public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        Person person = new Person("Alex", 100000);

        Class cl = person.getClass();
        System.out.println(cl); //class com.luxoft.querygenerator.entity.Person


        Entity clazzAnnotation = (Entity) cl.getAnnotation(Entity.class);
        System.out.println(clazzAnnotation);//@com.luxoft.querygenerator.domain.Entity(table=persons)

        String tableName = clazzAnnotation.table().isEmpty() ? cl.getName() : clazzAnnotation.table();
        System.out.println(tableName);//persons
        System.out.println("=======================");

        Field[] fields = cl.getDeclaredFields();

        for (int i = 0; i < fields.length; i++) {
//            fields[i].setAccessible(true);
            System.out.println(fields[i]);
            System.out.println("-----------------------");
            System.out.println(fields[i].getAnnotation(Id.class));
            System.out.println("+++++++++++++++++++++++++");
            System.out.println(fields[i].getAnnotatedType().toString());
            if (fields[i].getType().toString().equals("int") && fields[i].getAnnotation(Id.class) != null) {

                fields[i].setInt(person, fields[i].getInt(person)+1);
                System.out.println(fields[i].getInt(person));

            }
//            System.out.println(fields[i].getAnnotation(Id.class));
            System.out.println("====================");
        }


    }
}
