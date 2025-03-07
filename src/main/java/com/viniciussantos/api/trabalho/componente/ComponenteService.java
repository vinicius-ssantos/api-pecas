package com.viniciussantos.api.trabalho.componente;

import com.viniciussantos.api.trabalho.peca.PecaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComponenteService {
    @Autowired
    private ComponenteRepository componenteRepository;
    @Autowired
    private PecaRepository pecaRepository;

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
