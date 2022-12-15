package com.javy.ElMundoDelCelular.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="USERS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "User")
    private String user;
    @Column(name = "Pass")
    private String pass;
    @Column(name = "Nombre")
    private String nombre;
    @Column(name="rol")
    private int rol;
    @Column(name="CuentaNro")
    private int cuentaNro;
    
}
