package com.viniciussantos.api.trabalho.peca;

import com.viniciussantos.api.trabalho.componente.Componente;
import com.viniciussantos.api.trabalho.componente.ComponenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PecaService {
    @Autowired
    private PecaRepository pecaRepository;
    @Autowired
    private ComponenteRepository componenteRepository;

    public Peca criarPeca(Peca peca) {
        return pecaRepository.save(peca);
    }

    public Optional<Peca> buscarPeca(String codigo) {
        return pecaRepository.findByCodigo(codigo);
    }

    public List<Peca> listarPecas() {
        return pecaRepository.findAll();
    }

    public Optional<Componente> adicionarComponente(String codigo, Componente componente) {
        if (pecaRepository.findByCodigo(codigo).isPresent()) {
            componente.setCodigo(codigo);
            return Optional.of(componenteRepository.save(componente));
        }
        return Optional.empty();
    }

    public List<Componente> listarComponentes(String codigo) {
        return componenteRepository.findByCodigo(codigo);
    }
}
