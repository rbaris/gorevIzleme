package com.example.gorevizleme.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long userID;
    public String username;
    public String email;
    public String password;
    public String name;
    public String surname;
    public String telno;

    @ManyToMany(fetch = FetchType.EAGER)
    Collection<UserRole> roles = new ArrayList<>();
}