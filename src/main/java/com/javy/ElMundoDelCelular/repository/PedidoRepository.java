package com.javy.ElMundoDelCelular.repository;

import java.sql.Date;
import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.javy.ElMundoDelCelular.entities.Pedido;

public interface PedidoRepository extends CrudRepository<Pedido, Integer> {
  
    @Transactional(readOnly = true)
    @Query(nativeQuery = true, value = "SELECT * FROM dbCel01.PEDIDO")
    List<Pedido> findAllPedido();

    @Transactional(readOnly = true)
    @Query(nativeQuery = true, value = "SELECT c_p.idCuenta, pd.id, pd.Fecha, pd.PrecioFinal FROM dbCel01.COMPRAS_PEDIDOS as c_p INNER JOIN dbCel01.PEDIDO as pd ON c_p.idPedido = pd.id WHERE c_p.idCuenta = :idCuenta")
    List<Object[]> findAllPedidoById(@Param ("idCuenta") int idCuenta);

    @Transactional
    @Modifying  
    @Query(value = "INSERT into dbCel01.PEDIDO (Fecha, PrecioFinal) "
    		+ "value (:fecha, :precioTotal)", nativeQuery = true)
    void addPedido(@Param("fecha") ZonedDateTime fecha, @Param("precioTotal") Double precioTotal);

    @Transactional
    @Modifying  
    @Query(value = "INSERT into dbCel01.PEDIDO_EQUIPOS (IdPedido, IdEquipo) "
    		+ "value (:pedido, :equipo)", nativeQuery = true)
    void addPedido_Equipos(@Param("pedido") int pedido, @Param("equipo") int equipo);

}
