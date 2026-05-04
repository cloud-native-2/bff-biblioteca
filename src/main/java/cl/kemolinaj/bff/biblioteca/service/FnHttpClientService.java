package cl.kemolinaj.bff.biblioteca.service;

import cl.kemolinaj.bff.biblioteca.dtos.event.CorreoRqDto;
import cl.kemolinaj.bff.biblioteca.dtos.graphql.GraphQlRqDto;
import cl.kemolinaj.bff.biblioteca.dtos.libros.LibrosRqDto;
import cl.kemolinaj.bff.biblioteca.dtos.libros.LibrosRsDto;
import cl.kemolinaj.bff.biblioteca.dtos.prestamos.PrestamoRqDto;
import cl.kemolinaj.bff.biblioteca.dtos.prestamos.PrestamoRsDto;
import cl.kemolinaj.bff.biblioteca.dtos.usuarios.UsuarioDto;
import cl.kemolinaj.bff.biblioteca.utils.Constantes;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;

@Service( "fnHttpClientService")
@RequiredArgsConstructor
public class FnHttpClientService {
    @Value("${function.azure.url.base}")
    private String URL_BASE_FN;

    private final WebClient.Builder webClientBuilder;
    private final EventGridService eventGridService;

    public Mono<List<LibrosRsDto>> listarLibros() {
        WebClient webClient = webClientBuilder.build();
        return webClient
                .get()
                .uri(URL_BASE_FN.concat("libros"))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<>() {});
    }

    public Mono<String> ingresarLibro(LibrosRqDto rqDto) {
        WebClient webClient = webClientBuilder.build();
        return webClient
                .post()
                .uri(URL_BASE_FN.concat("libros"))
                .body(Mono.just(rqDto), LibrosRqDto.class)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<>() {});
    }

    public Mono<List<UsuarioDto>> listarUsuarios() {
        WebClient webClient = webClientBuilder.build();
        return webClient
                .get()
                .uri(URL_BASE_FN.concat("usuarios"))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<>() {});
    }

    public String ingresarUsuario(UsuarioDto rqDto) {
        WebClient webClient = webClientBuilder.build();
        Mono<UsuarioDto> response = webClient
                .post()
                .uri(URL_BASE_FN.concat("usuarios"))
                .body(Mono.just(rqDto), UsuarioDto.class)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<>() {});
        UsuarioDto rsDto = response.block();
        String mensajeCorreo = Constantes.MENSAJE_CORREO_NEW_USUARIO.replace("{NOMBRE_USUARIO}", rsDto.nomCompleto());
        CorreoRqDto correoRqDto = new CorreoRqDto(rsDto.correo(), Constantes.ASUNTO_CORREO_NEW_USUARIO, mensajeCorreo);
        eventGridService.enviarCorreoEvent(correoRqDto);
        return "Usuario Registrado";
    }

    public Mono<List<PrestamoRsDto>> listarPrestamos() {
        WebClient webClient = webClientBuilder.build();
        return webClient
                .get()
                .uri(URL_BASE_FN.concat("prestamos"))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<>() {});
    }

    public Mono<String> ingresarPrestamo(PrestamoRqDto rqDto) {
        WebClient webClient = webClientBuilder.build();
        Mono<PrestamoRsDto> rsDtoMono = webClient
                .post()
                .uri(URL_BASE_FN.concat("prestamos"))
                .body(Mono.just(rqDto), PrestamoRqDto.class)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<>() {});

        PrestamoRsDto rsDto = rsDtoMono.block();
        String mensaje = Constantes.MENSAJE_CORREO_PRESTAMO.replace("{NOMBRE_USUARIO}", rsDto.username().nomCompleto()).replace("{NOMBRE_LIBRO}", rsDto.libro().nombre());
        CorreoRqDto correoRqDto = new CorreoRqDto(rsDto.username().correo(), Constantes.ASUNTO_CORREO_PRESTAMO, mensaje);
        eventGridService.enviarCorreoEvent(correoRqDto);
        eventGridService.generarComprobante();
        return Mono.just(mensaje);
    }

    public Mono<Object> graphPrestamos(GraphQlRqDto rqDto) {
        WebClient webClient = webClientBuilder.build();
        return webClient
                .post()
                .uri(URL_BASE_FN.concat("graphqlPretamos"))
                .body(Mono.just(rqDto), GraphQlRqDto.class)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<>() {});
    }

    public Mono<Object> graphLibros(GraphQlRqDto rqDto) {
        WebClient webClient = webClientBuilder.build();
        return webClient
                .post()
                .uri(URL_BASE_FN.concat("graphqlLibros"))
                .body(Mono.just(rqDto), GraphQlRqDto.class)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<>() {});
    }
}
