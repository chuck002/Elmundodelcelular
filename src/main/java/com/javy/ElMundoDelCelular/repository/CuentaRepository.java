package com.javy.ElMundoDelCelular.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.javy.ElMundoDelCelular.entities.Cuenta;


public interface CuentaRepository extends CrudRepository<Cuenta, Integer> {

    @Transactional(readOnly = true)
    @Query(nativeQuery = true, value = "SELECT * FROM dbCel01.CUENTA")
    List<Cuenta> findAllPedido();

    @Transactional(readOnly = true)
    @Query(nativeQuery = true, value = "SELECT * FROM dbCel01.CUENTA as pd where pd.id = :id")
    List<Cuenta> findAllPedidoById(Integer id);
    
}
