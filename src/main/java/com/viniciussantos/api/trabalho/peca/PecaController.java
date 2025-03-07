package com.viniciussantos.api.trabalho.peca;

import com.viniciussantos.api.trabalho.componente.Componente;
import com.viniciussantos.api.trabalho.componente.ComponenteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/peca")
@RequiredArgsConstructor
class PecaController {

    private final PecaService pecaService;
    private final ComponenteService componenteService;

    @PostMapping
    public ResponseEntity<Peca> criarPeca(@Valid @RequestBody Peca peca) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pecaService.criarPeca(peca));
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Peca> buscarPeca(@PathVariable String codigo) {
        return pecaService.buscarPeca(codigo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Peca>> listarPecas() {
        List<Peca> pecas = pecaService.listarPecas();
        if (pecas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pecas);
    }
    @PostMapping("/{codigo}/componente")
    public ResponseEntity<Componente> adicionarComponente(@PathVariable String codigo, @Valid @RequestBody Componente componente) {
        return componenteService.adicionarComponente(codigo, componente)
                .map(comp -> ResponseEntity.status(HttpStatus.CREATED).body(comp))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{codigo}/componente")
    public ResponseEntity<List<Componente>> listarComponentesPorPeca(@PathVariable String codigo) {
        List<Componente> componentes = componenteService.listarComponentes(codigo);
        if (componentes.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(componentes);
    }
}
