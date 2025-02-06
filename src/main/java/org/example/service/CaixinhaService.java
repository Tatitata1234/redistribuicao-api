package org.example.service;

import org.example.controller.request.CaixinhaRequest;
import org.example.mapper.CaixinhaMapper;
import org.example.model.entity.Caixinha;
import org.example.repository.CaixinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CaixinhaService {

    @Autowired
    private CaixinhaRepository caixinhaRepository;

    public void edita(CaixinhaRequest request) {
        Caixinha nova = CaixinhaMapper.toEntity(request);

        Optional<Caixinha> velha = caixinhaRepository.findById(nova.getId());

        if (velha.isEmpty()) {
            throw new RuntimeException("Caixinha não encontrada");
        }

        Caixinha entidade = velha.get();

        entidade.setQuitada(nova.isQuitada());
        entidade.setMensagem(nova.getMensagem());
        entidade.setArrecadado(nova.getArrecadado());
        //entidade.setClassificacao(nova.getClassificacao());
        entidade.setDataVencimento(nova.getDataVencimento());
        entidade.setNome(nova.getNome());
        entidade.setTotal(nova.getTotal());
        //entidade.setUtililidade(nova.getUtililidade());
        entidade.setVencimentoProgramado(nova.isVencimentoProgramado());

        caixinhaRepository.save(entidade);
    }
}
