package com.robf3d.Desafio_literalura.principal;
import com.robf3d.Desafio_literalura.model.*;
import com.robf3d.Desafio_literalura.repository.AutorRepository;
import com.robf3d.Desafio_literalura.repository.LibroRepository;
import com.robf3d.Desafio_literalura.service.ConsumoAPI;
import com.robf3d.Desafio_literalura.service.ConvierteDatos;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;


public class Principal {
    Scanner teclado = new Scanner(System.in);
    String URL_INICIO = "https://gutendex.com/books/";
    ConsumoAPI consumoAPI = new ConsumoAPI();
    ConvierteDatos conversor = new ConvierteDatos();

    private LibroRepository lRepository;
    private AutorRepository ARepository;
    public Principal(LibroRepository libroRepository , AutorRepository autorRepository) {
        lRepository=libroRepository;
        ARepository = autorRepository;
    }


    public void request(){

        int seleccion = -1;

        while (seleccion != 0){
            System.out.println("""
                    ********** Menu **********
                    1 - Buscar Libro por titulo
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en determinado año
                    5 - Listar libros por idioma
                    0 - Salir
                    """);
            seleccion = Integer.parseInt(teclado.nextLine());
            switch (seleccion){
                case 1:
                    buscarlibro();
                    break;
                case 2:
                    listarLibrosRegistrado();
                    break;
                case 3:
                    ListarAutoresRegistrados();
                    break;
                case 4:
                    ListarAutoresVivos();
                    break;
                case 5:
                    listarLibrosPorIdioma();
                    break;
                default:
                    System.out.println("Saliendo de la aplicacion...");
                    break;
            }
        }
    }

    private void listarLibrosPorIdioma() {
        System.out.println("""
                    ********** Idiomas **********
                    1 - Es - Español
                    2 - En - Inglés
                    3 - Fr - frances
                    4 - Pt - Portugués
                    """);
        var idiomaSeleccionado = Integer.parseInt(teclado.nextLine());

        switch (idiomaSeleccionado){
            case 1:
                List<Libro> libroBuscadoEs=lRepository.obtenerLibroPorIdiomaEs();
                libroBuscadoEs.forEach(l -> System.out.println(l));
                break;
            case 2:
                List<Libro> libroBuscadoEn=lRepository.obtenerLibroPorIdiomaEn();
                libroBuscadoEn.forEach(l -> System.out.println(l));
                break;
            case 3:
                List<Libro> libroBuscadoFr=lRepository.obtenerLibroPorIdiomaFr();
                libroBuscadoFr.forEach(l -> System.out.println(l));
                break;
            case 4:
                List<Libro> libroBuscadoPr=lRepository.obtenerLibroPorIdiomaPr();
                libroBuscadoPr.forEach(l -> System.out.println(l));
                break;
            default:
                System.out.println("No se encontro ningun libro en idioma seleccionado");
        }

    }
    private void ListarAutoresVivos() {
        System.out.println("Ingrese el año donde estava vivo el autor que buscas");
        var autorVivo = Integer.parseInt(teclado.nextLine());
        List<Autor> autoresBuscados = ARepository.obtenerAutoresVivos(autorVivo);
        if(autoresBuscados.size() > 0){
            autoresBuscados.forEach(a -> System.out.println(a));
        }else{
            System.out.println("No se encontro ningun registro");
        }
    }

    private void ListarAutoresRegistrados() {
        List<Libro> libroBuscado=lRepository.obtenerTodosLosLibros();
        List<Autor> autoresBuscados = ARepository.obtenerTodosLosAutores();
        if(autoresBuscados.size()>0){
            System.out.println("\n" + "-----Autores registrados-----" + "\n");
            libroBuscado.forEach(l -> System.out.println("""
                            **********AUTOR*********
                            Autor = %s
                            Fecha de nacimiento = %s
                            Fecha de muerte = %s
                            Libros = %s
                            *************************
                            """.formatted(l.getAutor().getNombre(),
                    l.getAutor().getFechaDeNacimiento(),
                    l.getAutor().getFechaDeMuerte(),
                    l.getTitulo())));
        }else{
            System.out.println("No se encontro ningun registro");
        }
    }

    private void listarLibrosRegistrado() {
        //Optional<Libro>libroBuscado=lRepository.findByTituloContainsIgnoreCase(busqueda);
        List<Libro> libroBuscado=lRepository.obtenerTodosLosLibros();
        if(libroBuscado.size()>0){
            System.out.println("\n" + "-----Libros registrados-----" + "\n");
            libroBuscado.forEach(l -> System.out.println(l));
        }else{
            System.out.println("No se encontro ningun registro");
        }
    }

    private void buscarlibro() {
        System.out.println("Escribe el titulo de libro que deseas buscar");
        String librobusqueda = teclado.nextLine().replace(" ","+").toLowerCase();
        var busqueda = BuscarlibroJson(librobusqueda);
        //System.out.println(busqueda);
        if(busqueda != null) {
            // ingresar libro a la base de datos
            var primerAutor = busqueda.autor().get(0);
            var autor = new Autor(primerAutor.nombre(),
                    primerAutor.fechaDeNacimiento(),
                    primerAutor.fechaDeMuerte()
            );

            var libro = new Libro(busqueda.titulo(),
                    busqueda.idiomas(),
                    busqueda.descargasTotales(),
                    autor);

            ARepository.save(autor);
            lRepository.save(libro);
            System.out.println(libro);
//        System.out.println(autor);
        }
    }

    public Datoslibros BuscarlibroJson(String busqueda){
        var json = consumoAPI.obtenerDatos(URL_INICIO + "?search=" + busqueda);
        DatosLibroResultados datos = conversor.obtenerDatos(json,DatosLibroResultados.class);
        if(datos.ResultadosJson().size()==0){
            System.out.println("El libro no existe");
            return null;
        }else{
            return datos.ResultadosJson().get(0);
        }
    }
}
