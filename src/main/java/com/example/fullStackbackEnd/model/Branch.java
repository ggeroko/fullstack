package com.example.fullStackbackEnd.model;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "branch")
public class Branch {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String name;
    @Column
    private String location;
    public Branch(long id, String name, String location ) {
        this.id=id;
        this.name=name;
        this.location=location;
    }
    public Branch( String name, String location ) {
        this.name=name;
        this.location=location;
    }

    public Branch(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Branch{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
