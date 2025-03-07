package com.viniciussantos.api.trabalho.componente;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/componente")
@RequiredArgsConstructor
class ComponenteController {
    private final ComponenteService componenteService;

    @PostMapping("/{codigo}")
    public ResponseEntity<Componente> adicionarComponente(@PathVariable String codigo, @RequestBody Componente componente) {
        return componenteService.adicionarComponente(codigo, componente)
                .map(comp -> ResponseEntity.status(HttpStatus.CREATED).body(comp))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<List<Componente>> listarComponentes(@PathVariable String codigo) {
        List<Componente> componentes = componenteService.listarComponentes(codigo);
        if (componentes.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(componentes);
    }
}