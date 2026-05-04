package cl.kemolinaj.bff.biblioteca.controlers;

import cl.kemolinaj.bff.biblioteca.dtos.event.CorreoRqDto;
import cl.kemolinaj.bff.biblioteca.service.EventGridService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/event")
@RequiredArgsConstructor
public class EventGridController {
    private final EventGridService eventGridService;

    @PostMapping("/envia")
    public ResponseEntity<Map<String, String>> enviarEvento() {
        log.info("Enviando evento");
        eventGridService.enviarCorreoEvent(new CorreoRqDto("prueba", "asunto test", "este es el cuerpo del correo"));
        log.info("Evento enviado");
        Map<String, String> response = Map.of("message", "Evento enviado");
        return ResponseEntity.ok(response);
    }
}
