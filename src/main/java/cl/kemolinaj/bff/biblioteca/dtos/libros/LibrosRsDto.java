package cl.kemolinaj.bff.biblioteca.dtos.libros;

import io.soabase.recordbuilder.core.RecordBuilder;
import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;

@RecordBuilder
@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
public record LibrosRsDto (
        Long id,
        String nombre,
        String editorial
) {
}
