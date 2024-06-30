package com.robf3d.Desafio_literalura.repository;



import com.robf3d.Desafio_literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutorRepository extends JpaRepository<Autor,Long> {
    @Query(value = "SELECT * FROM autores",nativeQuery = true)
    List<Autor> obtenerTodosLosAutores();

    @Query(value = "SELECT * FROM autores a where :fecha > a.fecha_de_nacimiento and :fecha < a.fecha_de_muerte",nativeQuery = true)
    List<Autor> obtenerAutoresVivos(int fecha);
}
