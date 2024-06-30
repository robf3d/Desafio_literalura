package com.robf3d.Desafio_literalura.model;


import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@OneToOne
    //@JoinColumn(name = "libro_id", referencedColumnName = "id")
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL)
    private List<Libro> libro;

    String nombre;
    private int fechaDeNacimiento;
    private int fechaDeMuerte;

    public  Autor(){}

    public Autor(String nombre, int fechaDeNacimiento, int fechaDeMuerte ) {
        this.nombre = nombre;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.fechaDeMuerte = fechaDeMuerte;
        //this.libro = libro1;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(int fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public int getFechaDeMuerte() {
        return fechaDeMuerte;
    }

    public void setFechaDeMuerte(int fechaDeMuerte) {
        this.fechaDeMuerte = fechaDeMuerte;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public Libro getLibro() {
//        return libro;
//    }
//
//    public void setLibro(Libro libro) {
//        this.libro = libro;
//    }


    public List<Libro> getLibro() {
        return libro;
    }

    public void setLibro(List<Libro> libro) {
        libro.forEach(l-> l.setAutor(this));
        this.libro = libro;
    }

    @Override
    public String toString() {
        return "\n" + "**********AUTOR**********"+ "\n" +
                "nombre= " + nombre + "\n" +
                "fechaDeNacimiento= " + fechaDeNacimiento + "\n" +
                "fechaDeMuerte= " + fechaDeMuerte +
                "\n" + "*************************"+ "\n";
    }
}
