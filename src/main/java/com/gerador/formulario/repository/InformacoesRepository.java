package com.gerador.formulario.repository;

import com.gerador.formulario.model.entity.Informacoes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InformacoesRepository extends JpaRepository<Informacoes, Long> {
    public Informacoes save(Informacoes informacoes);
}
