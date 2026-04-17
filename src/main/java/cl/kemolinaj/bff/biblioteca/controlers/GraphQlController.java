package cl.kemolinaj.bff.biblioteca.controlers;

import cl.kemolinaj.bff.biblioteca.dtos.graphql.GraphQlRqDto;
import cl.kemolinaj.bff.biblioteca.service.FnHttpClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/graphql")
@RequiredArgsConstructor
public class GraphQlController {
    private final FnHttpClientService fnRestClient;

    @PostMapping("/graphqlPretamos")
    public Object graphqlPrestamos(
            @RequestBody GraphQlRqDto rqDto
            ) {
        return ResponseEntity.ok(fnRestClient.graphPrestamos(rqDto).block());
    }

    @PostMapping("/graphqlLibros")
    public Object graphqlLibros(
            @RequestBody GraphQlRqDto rqDto
            ) {
        return ResponseEntity.ok(fnRestClient.graphPrestamos(rqDto).block());
    }
}
