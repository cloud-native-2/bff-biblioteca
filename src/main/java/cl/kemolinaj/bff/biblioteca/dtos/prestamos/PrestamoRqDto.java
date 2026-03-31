package cl.kemolinaj.bff.biblioteca.dtos.prestamos;

import io.soabase.recordbuilder.core.RecordBuilder;
import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;

@RecordBuilder
@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
public record PrestamoRqDto(
        String username,
        Long idLibro
) {
}
