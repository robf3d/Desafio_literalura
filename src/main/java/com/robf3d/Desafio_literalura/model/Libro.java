package com.robf3d.Desafio_literalura.model;


import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "Libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;
    //@OneToOne(mappedBy = "libro", cascade = CascadeType.ALL, fetch = FetchType.LAZY,optional = false)
    @ManyToOne
    @JoinColumn(name = "autor_id", referencedColumnName = "id")
    private Autor autor;
    private List<String> idiomas;
    private int descargasTotales;

    public Libro(){}

    public Libro(String titulo, List<String> idiomas, int descargasTotales, Autor autor1 ) {
        this.titulo = titulo;
        this.idiomas = idiomas;
        this.descargasTotales = descargasTotales;
        this.autor=autor1;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
       // autor.setLibro(this);
        this.autor = autor;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
    }

    public int getDescargasTotales() {
        return descargasTotales;
    }

    public void setDescargasTotales(int descargasTotales) {
        this.descargasTotales = descargasTotales;
    }


    @Override
    public String toString() {
        return "\n" + "**********LIBRO**********" + "\n" +
                "Titulo = " + titulo + "\n" +
                "Autor = " + autor.getNombre() + "\n" +
                "Idiomas = " + idiomas.get(0) + "\n" +
                "DescargasTotales = " + descargasTotales + "\n" +
                "*************************" + "\n";
    }
}
