package cl.kemolinaj.bff.biblioteca.dtos.libros;

import io.soabase.recordbuilder.core.RecordBuilder;
import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;

import java.io.Serializable;

@RecordBuilder
@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
public record LibrosRqDto(
        String nombre,
        String editorial
) {
}
