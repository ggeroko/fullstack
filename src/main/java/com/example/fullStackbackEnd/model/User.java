package com.example.fullStackbackEnd.model;
import jakarta.persistence.*;

import java.util.Objects;


@Entity
@Table(name = "users")//"user" is reserved in h2
public class User {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String username;

    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;
    @Column
    private String name;
    @Column
    private String email;


    public User(Long id, String username, Branch branch, String name, String email) {
        this.id = id;
        this.username = username;
        this.branch = branch;
        this.name = name;
        this.email = email;
    }


    public User(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
