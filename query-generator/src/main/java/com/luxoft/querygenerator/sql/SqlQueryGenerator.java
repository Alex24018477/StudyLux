package com.luxoft.querygenerator.sql;

import com.luxoft.querygenerator.api.QueryGenerator;
import com.luxoft.querygenerator.domain.Column;
import com.luxoft.querygenerator.domain.Entity;
import com.luxoft.querygenerator.domain.Id;

import java.lang.reflect.Field;
import java.util.StringJoiner;

public class SqlQueryGenerator implements QueryGenerator {
    @Override
    public String findAll(Class<?> clazz) {
        if (!clazz.isAnnotationPresent(Entity.class)) {
            throw new IllegalArgumentException("Annotation @Entity should be present");
        }
        StringBuilder query = new StringBuilder("SELECT ");
        Entity clazzAnnotation = clazz.getAnnotation(Entity.class);
        String tableName = clazzAnnotation.table().isEmpty() ? clazz.getName() : clazzAnnotation.table();

        StringJoiner columnNames = new StringJoiner(", ");
        for (Field declaredField : clazz.getDeclaredFields()) {
            Column columnAnnotation = declaredField.getAnnotation(Column.class);
            if (columnAnnotation != null) {
                String columnNameFromAnnotation = columnAnnotation.name();
                String columnName = columnNameFromAnnotation.isEmpty() ? declaredField.getName()
                        : columnNameFromAnnotation;

                columnNames.add(columnName);
            }
        }

        query.append(columnNames);
        query.append(" FROM ");
        query.append(tableName);
        query.append(";");
        // SELECT id, person_name, salary from persons;
        return query.toString();
    }

//    @Override
    public String findById(Class<?> clazz, Object id) {
        StringBuilder query = new StringBuilder(findAll(clazz));
        query.deleteCharAt(query.length()-1);
        query.append(" WHERE id = '");
        query.append(id + "';");
        // SELECT id, person_name, salary from persons;
        return query.toString();
    }



    //INSERT INTO users (name, age) VALUES ('Сергей', '25');
    @Override
    public String insert(Object value) throws IllegalAccessException {
        Class clazz = value.getClass();
        if (!clazz.isAnnotationPresent(Entity.class)) {
            throw new IllegalArgumentException("Annotation @Entity should be present");
        }
        StringBuilder query = new StringBuilder("INSERT INTO ");
        Entity clazzAnnotation = (Entity) clazz.getAnnotation(Entity.class);
        String tableName = clazzAnnotation.table().isEmpty() ? clazz.getName() : clazzAnnotation.table();

        StringJoiner columnNames = new StringJoiner(", ", "(", ")");
        for (Field declaredField : clazz.getDeclaredFields()) {
            if (declaredField.getType().toString().equals("int") && declaredField.getAnnotation(Id.class) != null){
                continue;
            }
            //            ID++
//            declaredField.setAccessible(true);
//            if (declaredField.getType().toString().equals("int") && declaredField.getAnnotation(Id.class) != null) {
//                declaredField.setInt(value, declaredField.getInt(value)+1);
//            }

            Column columnAnnotation = declaredField.getAnnotation(Column.class);
            if (columnAnnotation != null) {
                String columnNameFromAnnotation = columnAnnotation.name();
                String columnName = columnNameFromAnnotation.isEmpty() ? declaredField.getName()
                        : columnNameFromAnnotation;

                columnNames.add(columnName);
            }
        }

        query.append(tableName);
        query.append(columnNames);
        query.append(" VALUES ");
        query.append("(");

        

        query.append(")");
        // SELECT id, person_name, salary from persons;
        return query.toString();



    }


    //DELETE FROM users WHERE id = '10';
    @Override
    public String remove(Class<?> clazz, Object id) {
//        findAll()
        return null;
    }


    //UPDATE users SET age = '18' WHERE id = '3';
    @Override
    public String update(Object value) {
        return null;
    }

    private  void setNextID(Class cl){

    }
}
