package com.javy.ElMundoDelCelular.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.javy.ElMundoDelCelular.entities.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer>{

    @Transactional(readOnly = true)
    @Query(nativeQuery = true, value = "SELECT * FROM dbCel.users WHERE user = :user AND pass = :pass")
    List<Object[]> findByUsuario(@Param ("user") String user, @Param ("pass") String pass);

    
}
