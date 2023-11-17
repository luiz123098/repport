package com.gerador.formulario.model.services;

import com.gerador.formulario.model.entity.Informacoes;
import com.gerador.formulario.repository.InformacoesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class InformacoesService {

    @Autowired
    InformacoesRepository informacoesRepository;
    public Informacoes registrarInformacoes(Informacoes informacoes){
            informacoesRepository.save(informacoes);
        return informacoes;
    }

    public List<Informacoes> findAll() {
        return informacoesRepository.findAll();
    }
}
