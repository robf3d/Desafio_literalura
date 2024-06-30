package com.robf3d.Desafio_literalura.repository;

import com.robf3d.Desafio_literalura.model.Autor;
import com.robf3d.Desafio_literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LibroRepository extends JpaRepository<Libro,Long> {
    Optional<Libro> findByTituloContainsIgnoreCase(String nombreLibro);

    @Query(value = "SELECT * FROM libros",nativeQuery = true)
    List<Libro> obtenerTodosLosLibros();

//    @Query(value = "SELECT l.idiomas, l.descargas_totales, l.autor_id, l.id, l.titulo FROM autores a join libros l on a.id = l.autor_id ",nativeQuery = true)
//    List<Libro> obtenerLibrosDelAutor(List<Autor> autores);

    @Query(value = "SELECT * FROM libros WHERE idiomas @> '{en}'",nativeQuery = true)
    List<Libro> obtenerLibroPorIdiomaEn();

    @Query(value = "SELECT * FROM libros WHERE idiomas @> '{es}'",nativeQuery = true)
    List<Libro> obtenerLibroPorIdiomaEs();

    @Query(value = "SELECT * FROM libros WHERE idiomas @> '{fr}'",nativeQuery = true)
    List<Libro> obtenerLibroPorIdiomaFr();

    @Query(value = "SELECT * FROM libros WHERE idiomas @> '{pr}'",nativeQuery = true)
    List<Libro> obtenerLibroPorIdiomaPr();
}
