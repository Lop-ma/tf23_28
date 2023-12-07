package ru.tinkoff.qa.hibernate.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "workman", schema = "public")
public class Workman {
    @Id
    @Column(name = "id", nullable = false)
    int id;
    @Column(name = "\"name\"", nullable = false)
    String name;
    @Column(name = "age")
    int age;
    @Column(name = "\"positions\"")
    int positions;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getPositions() {
        return positions;
    }

    public void setPositions(int positions) {
        this.positions = positions;
    }
}
