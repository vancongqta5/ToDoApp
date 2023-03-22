package com.example.todoapp.models;


import javax.persistence.*;

@Entity
public class Users {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id",nullable = false)
    private Integer id;
    @Column(name = "username",length = 30)
    private String name;
    @Column(name = "email", length = 45)
    private String email;
    @Column(name = "password",length = 30,nullable = false)
    private String password;
    @Column(name = "id_role", length = 30)
    private String idRole;

    public Users(String name, String email, String password, String idRole) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.idRole = idRole;

    }

    public Users() {
    }
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
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getIdRole() {
        return idRole;
    }
    public void setIdRole(String idRole) {
        this.idRole = idRole;
    }
}