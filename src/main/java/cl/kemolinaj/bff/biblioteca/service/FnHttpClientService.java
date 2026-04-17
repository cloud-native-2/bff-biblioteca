package cl.kemolinaj.bff.biblioteca.service;

import cl.kemolinaj.bff.biblioteca.dtos.graphql.GraphQlRqDto;
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
import java.util.Objects;

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

    public Mono<String> ingresarUsuario(UsuarioDto rqDto) {
        WebClient webClient = webClientBuilder.build();
        return webClient
                .post()
                .uri(URL_BASE_FN.concat("usuarios"))
                .body(Mono.just(rqDto), LibrosRqDto.class)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<>() {});
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
        return webClient
                .post()
                .uri(URL_BASE_FN.concat("prestamos"))
                .body(Mono.just(rqDto), LibrosRqDto.class)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<>() {});
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
