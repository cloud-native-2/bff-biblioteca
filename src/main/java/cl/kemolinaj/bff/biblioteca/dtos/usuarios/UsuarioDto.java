package cl.kemolinaj.bff.biblioteca.dtos.usuarios;

import io.soabase.recordbuilder.core.RecordBuilder;
import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;

@RecordBuilder
@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
public record UsuarioDto(
        String username,
        String correo,
        String nomCompleto,
        String run
) {
}
