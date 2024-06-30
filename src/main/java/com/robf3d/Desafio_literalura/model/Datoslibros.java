package com.robf3d.Desafio_literalura.model;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)

public record Datoslibros(@JsonAlias("title")
                          String titulo,
                          @JsonAlias("authors")
                          List<DatosAutor> autor,
                          @JsonAlias("languages")
                          List<String> idiomas,
                          @JsonAlias("download_count")
                          int descargasTotales) {
}



