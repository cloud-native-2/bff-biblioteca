package cl.kemolinaj.bff.biblioteca.controlers;

import cl.kemolinaj.bff.biblioteca.dtos.libros.LibrosRqDto;
import cl.kemolinaj.bff.biblioteca.dtos.libros.LibrosRsDto;
import cl.kemolinaj.bff.biblioteca.dtos.prestamos.PrestamoRqDto;
import cl.kemolinaj.bff.biblioteca.dtos.prestamos.PrestamoRsDto;
import cl.kemolinaj.bff.biblioteca.dtos.usuarios.UsuarioDto;
import cl.kemolinaj.bff.biblioteca.service.FnHttpClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiController {
    private final FnHttpClientService fnService;

    // Libros

    @GetMapping("/libros")
    public ResponseEntity<List<LibrosRsDto>> listarLibros() {
        return ResponseEntity.ok(fnService.listarLibros().block());
    }

    @PostMapping("/libros")
    public ResponseEntity<String> ingresarLibro(@RequestBody LibrosRqDto libro) {
        return ResponseEntity.ok(fnService.ingresarLibro(libro).block());
    }

    // Usuarios

    @GetMapping("/usuarios")
    public ResponseEntity<List<UsuarioDto>> listarUsuarios() {
        return ResponseEntity.ok(fnService.listarUsuarios().block());
    }

    @PostMapping("/usuarios")
    public ResponseEntity<String> ingresarUsuario(@RequestBody UsuarioDto usuario) {
        return ResponseEntity.ok(fnService.ingresarUsuario(usuario).block());
    }

    // Préstamos

    @GetMapping("/prestamos")
    public ResponseEntity<List<PrestamoRsDto>> listarPrestamos() {
        return ResponseEntity.ok(fnService.listarPrestamos().block());
    }

    @PostMapping("/prestamos")
    public ResponseEntity<String> ingresarPrestamo(@RequestBody PrestamoRqDto prestamo) {
        return ResponseEntity.ok(fnService.ingresarPrestamo(prestamo).block());
    }

}
