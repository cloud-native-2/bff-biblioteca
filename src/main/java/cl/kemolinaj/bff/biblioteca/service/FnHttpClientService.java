package cl.kemolinaj.bff.biblioteca.service;

import cl.kemolinaj.bff.biblioteca.dtos.libros.LibrosRqDto;
import cl.kemolinaj.bff.biblioteca.dtos.libros.LibrosRsDto;
import cl.kemolinaj.bff.biblioteca.dtos.prestamos.PrestamoRqDto;
import cl.kemolinaj.bff.biblioteca.dtos.prestamos.PrestamoRsDto;
import cl.kemolinaj.bff.biblioteca.dtos.usuarios.UsuarioDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service( "fnHttpClientService")
@RequiredArgsConstructor
public class FnHttpClientService {
    @Value("${function.azure.url.base}")
    private String URL_BASE_FN;

    private final WebClient.Builder webClientBuilder;

    public Mono<List<LibrosRsDto>> listarLibros() {
        WebClient webClient = webClientBuilder.build();
        return webClient
                .get()
                .uri(URL_BASE_FN.concat("ListarLibros"))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<>() {});
    }

    public Mono<String> ingresarLibro(LibrosRqDto rqDto) {
        WebClient webClient = webClientBuilder.build();
        return webClient
                .post()
                .uri(URL_BASE_FN.concat("CrearLibro"))
                .body(Mono.just(rqDto), LibrosRqDto.class)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<>() {});
    }

    public Mono<List<UsuarioDto>> listarUsuarios() {
        WebClient webClient = webClientBuilder.build();
        return webClient
                .get()
                .uri(URL_BASE_FN.concat("ListarUsuario"))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<>() {});
    }

    public Mono<String> ingresarUsuario(UsuarioDto rqDto) {
        WebClient webClient = webClientBuilder.build();
        return webClient
                .post()
                .uri(URL_BASE_FN.concat("CrearUsuario"))
                .body(Mono.just(rqDto), LibrosRqDto.class)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<>() {});
    }

    public Mono<List<PrestamoRsDto>> listarPrestamos() {
        WebClient webClient = webClientBuilder.build();
        return webClient
                .get()
                .uri(URL_BASE_FN.concat("ListarPrestamos"))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<>() {});
    }

    public Mono<String> ingresarPrestamo(PrestamoRqDto rqDto) {
        WebClient webClient = webClientBuilder.build();
        return webClient
                .post()
                .uri(URL_BASE_FN.concat("CrearPrestamo"))
                .body(Mono.just(rqDto), LibrosRqDto.class)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<>() {});
    }
}
