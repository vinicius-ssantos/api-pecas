package com.viniciussantos.api.trabalho.componente;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComponenteRepository extends JpaRepository<Componente, Long> {
    List<Componente> findByCodigo(String codigo);
}
