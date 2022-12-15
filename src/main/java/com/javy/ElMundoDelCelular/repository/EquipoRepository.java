package com.javy.ElMundoDelCelular.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.javy.ElMundoDelCelular.entities.Marca;


public interface EquipoRepository extends CrudRepository<Marca, Integer> {
  
    @Transactional(readOnly = true)
    @Query(nativeQuery = true, value = "SELECT * FROM dbCel01.MARCA")
    List<Marca> findAllMarca();

    @Transactional(readOnly = true)
    @Query(nativeQuery = true, value = "SELECT mr.Marca as brand, eq.Imagen as imagen, eq.Nombre as name, eq.Precio as precio, eq.id as id FROM dbCel01.EQUIPO as eq join dbCel01.MARCA as mr on eq.Marca = mr.id order by RAND() Limit 15")
    List<Object[]> findAllBrands();

    @Transactional(readOnly = true)
    @Query(nativeQuery = true, value = "SELECT mr.Marca as brand, eq.Imagen as imagen, eq.Nombre as name, eq.Precio as precio, eq.id as id FROM dbCel01.EQUIPO as eq join dbCel01.MARCA as mr on eq.Marca = mr.id WHERE eq.Marca = :idBrand order by eq.Precio ASC Limit 15")
    List<Object[]> findAllPrecioMayor(@Param ("idBrand") int idBrand);

    @Transactional(readOnly = true)
    @Query(nativeQuery = true, value = "SELECT mr.Marca as brand, eq.Imagen as imagen, eq.Nombre as name, eq.Precio as precio, eq.id as id FROM dbCel01.EQUIPO as eq join dbCel01.MARCA as mr on eq.Marca = mr.id WHERE eq.Marca = :idBrand order by eq.Precio DESC Limit 15")
    List<Object[]> findAllPrecioMenor(@Param ("idBrand") int idBrand);

    @Transactional(readOnly = true)
    @Query(nativeQuery = true, value = "SELECT mr.Marca as brand, eq.Imagen as imagen, eq.Nombre as name, eq.Precio as precio, eq.id as id FROM dbCel01.EQUIPO as eq join dbCel01.MARCA as mr on eq.Marca = mr.id WHERE eq.Marca = :idBrand order by RAND() Limit 15")
    List<Object[]> findByIdBrand(@Param ("idBrand") int idBrand);

    @Transactional(readOnly = true)
    @Query(nativeQuery = true, value = "SELECT eq.id as id, mr.Marca as brand, eq.Nombre as name, eq.Caracteristicas as caracteristicas, eq.Imagen as imagen, eq.Precio as precio FROM dbCel01.EQUIPO as eq join dbCel01.MARCA as mr on eq.Marca = mr.id WHERE eq.id = :idEquipo")
    List<Object[]> findByIdEquipo(@Param ("idEquipo") int idEquipo);

    @Transactional(readOnly = true)
    @Query(nativeQuery = true, value = "SELECT p_e.IdPedido, eq.id,eq.Marca, eq.Nombre, eq.Caracteristicas, eq.Imagen, eq.Precio From dbCel01.PEDIDO_EQUIPOS as p_e INNER JOIN dbCel01.EQUIPO as eq ON p_e.idEquipos = eq.id where p_e.idPedido = :idPedido")
    List<Object[]> findByPedido(@Param ("idPedido") int idPedido);

}
