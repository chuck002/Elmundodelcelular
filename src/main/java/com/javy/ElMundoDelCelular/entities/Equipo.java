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
@Table(name = "EQUIPO")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Equipo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "Marca")
    private int brand;
    @Column(name = "Caracteristicas")
    private String description;
    @Column(name = "Imagen")
    private String img;
    @Column(name = "Nombre")
    private String model;
    @Column(name = "Precio")
    private double price;
    
}
