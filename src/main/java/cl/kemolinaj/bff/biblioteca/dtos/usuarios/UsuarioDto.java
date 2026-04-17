package cl.kemolinaj.bff.biblioteca.dtos.usuarios;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.soabase.recordbuilder.core.RecordBuilder;
import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;

@RecordBuilder
@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record UsuarioDto(
        String username,
        String correo,
        String nomCompleto,
        String run
) {
}
