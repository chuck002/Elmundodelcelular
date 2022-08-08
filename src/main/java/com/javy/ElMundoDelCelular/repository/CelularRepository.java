package com.javy.ElMundoDelCelular.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.javy.ElMundoDelCelular.entities.Marca;


public interface CelularRepository extends CrudRepository<Marca, Integer> {
  
    @Transactional(readOnly = true)
    @Query(nativeQuery = true, value = "SELECT * FROM `dbCel`.brand")
    List<Marca> findAllMarca();

    @Transactional(readOnly = true)
    @Query(nativeQuery = true, value = "SELECT br.brand as brand, cl.img as imagen, cl.model as name, cl.price as precio, cl.id as id FROM `dbCel`.celular as cl join `dbCel`.brand as br on cl.brand = br.id order by RAND() Limit 15")
    List<Object[]> findAllBrands();

    @Transactional(readOnly = true)
    @Query(nativeQuery = true, value = "SELECT br.brand as brand, cl.img as imagen, cl.model as name, cl.price as precio, cl.id as id FROM `dbCel`.celular as cl join `dbCel`.brand as br on cl.brand = br.id WHERE cl.brand = :idBrand order by RAND() Limit 15")
    List<Object[]> findByIdBrand(@Param ("idBrand") int idBrand);

    @Transactional(readOnly = true)
    @Query(nativeQuery = true, value = "SELECT br.brand as brand, cl.img as imagen, cl.model as name, cl.price as precio, cl.id as id FROM `dbCel`.celular as cl join `dbCel`.brand as br on cl.brand = br.id WHERE cl.id = :idCelular order by RAND() Limit 15")
    List<Object[]> findByCelular(@Param ("idCelular") int idCelular);

}
