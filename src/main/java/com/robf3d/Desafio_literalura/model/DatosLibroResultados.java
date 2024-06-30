package com.robf3d.Desafio_literalura.model;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibroResultados(
        @JsonAlias("results") List<Datoslibros> ResultadosJson
        ) {
}
