package com.example.kanflow.model;

import com.example.kanflow.model.Workspace;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Column;

import java.util.Set;



@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;
    private String firstname;
    private String lastname;
    private String avatarUrl;

    @ManyToMany(mappedBy = "members")
    private Set<Workspace> workspaces;

    // Getters and Setters

    // public Long getId() {
    //     return id;
    // }

    // public void setId(Long id) {
    //     this.id = id;
    // }

    // public String getEmail() {
    //     return email;
    // }

    // public void setEmail(String email) {
    //     this.email = email;
    // }

    // public String getFullname() {
    //     return fullname;
    // }

    // public void setFullname(String fullname) {
    //     this.fullname = fullname;
    // }

    // public String getFirstname() {
    //     return phone;
    // }

    // public void setPhone(String phone) {
    //     this.phone = phone;
    // }
}

