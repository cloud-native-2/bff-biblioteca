package cl.kemolinaj.bff.biblioteca.controlers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping
@RequiredArgsConstructor
public class HealthController {
    @GetMapping("/health")
    public String health() {
        log.info("Health check endpoint called");
        return "OK";
    }
}
