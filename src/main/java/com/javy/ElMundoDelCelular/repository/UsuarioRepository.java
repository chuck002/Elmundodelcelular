package com.javy.ElMundoDelCelular.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.javy.ElMundoDelCelular.entities.Users;

public interface UsuarioRepository extends CrudRepository<Users, Integer>{

    @Transactional(readOnly = true)
    @Query(nativeQuery = true, value = "SELECT * FROM dbCel01.USERS WHERE user = :user AND pass = :pass")
    List<Object[]> findByUsuario(@Param ("user") String user, @Param ("pass") String pass);

    @Transactional(readOnly = true)
    @Query(nativeQuery = true, value = "SELECT * FROM dbCel01.USERS")
    List<Object[]> findByAllUsuario();

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "INSERT INTO `dbCel01`.`USERS` (`Nombre`,`User`,`Pass`,`Rol`, `CuentaNro`) VALUES (:nombre, :user, :pass, :rol, :nroCuenta)")
    List<Object[]> setNuevoUsuario(String nombre, String user, String pass, Integer rol, Integer nroCuenta );

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "DELETE FROM `dbCel01`.`USERS` WHERE id = :id")
    void deleteUsuario(@Param("id")Integer id);
}
