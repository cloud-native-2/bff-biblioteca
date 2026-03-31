package cl.kemolinaj.bff.biblioteca.dtos.prestamos;

import cl.kemolinaj.bff.biblioteca.dtos.libros.LibrosRsDto;
import cl.kemolinaj.bff.biblioteca.dtos.usuarios.UsuarioDto;
import io.soabase.recordbuilder.core.RecordBuilder;
import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;

import java.time.LocalDateTime;

@RecordBuilder
@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
public record PrestamoRsDto(
        Long id,
        LocalDateTime fechaEntrega,
        LocalDateTime fechaDevolucion,
        UsuarioDto username,
        LibrosRsDto libro
) {
}
