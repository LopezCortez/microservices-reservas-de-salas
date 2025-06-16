package com.otavio.api_gateway.infrastructure.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/swagger")
public class SwaggerController {

    @GetMapping("/config")
    public Map<String, String> swaggerConfig() {
        Map<String, String> services = new HashMap<>();
        services.put("Salas Service", "/salas/v3/api-docs");
        services.put("Reservas Service", "/reservas/v3/api-docs");
        services.put("Usuario Service", "/usuarios/v3/api-docs");
        return services;
    }
}
