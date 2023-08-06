package com.example.gorevizleme.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class UserRole {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long roleID;
    public String roleName;

    public String toString(){
        return roleName;
    }

}