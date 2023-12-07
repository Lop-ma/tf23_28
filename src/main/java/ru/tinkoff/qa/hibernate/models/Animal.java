package ru.tinkoff.qa.hibernate.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "animal", schema = "public")
public class Animal {
    @Id
    @Column(name = "id", unique = true, nullable = false)
    int id;
    @Column(name = "\"name\"")
    String name;
    @Column(name = "age")
    int age;
    @Column(name = "\"type\"")
    int type;
    @Column(name = "sex")
    int sex;
    @ManyToOne
    @JoinColumn(name = "place")
    Places place;

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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public Places getPlace() {
        return place;
    }

    public void setPlace(Places place) {
        this.place = place;
    }
}
