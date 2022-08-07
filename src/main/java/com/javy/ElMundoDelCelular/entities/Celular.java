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
@Table(name = "celular")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Celular {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "brand")
    private int brand;
    @Column(name = "description")
    private String description;
    @Column(name = "img")
    private String img;
    @Column(name = "model")
    private String model;
    @Column(name = "price")
    private double price;
    
}
