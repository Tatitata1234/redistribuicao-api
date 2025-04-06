package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.controller.request.CaixinhaRequest;
import org.example.exception.CaixinhaNaoEncontradaException;
import org.example.mapper.CaixinhaMapper;
import org.example.model.entity.Caixinha;
import org.example.repository.CaixinhaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CaixinhaService {

    private final CaixinhaRepository caixinhaRepository;

    public void edita(CaixinhaRequest request) {
        Caixinha nova = CaixinhaMapper.toEntity(request);

        Optional<Caixinha> velha = caixinhaRepository.findById(nova.getId());

        if (velha.isEmpty()) {
            throw new CaixinhaNaoEncontradaException("Caixinha não encontrada");
        }

        Caixinha entidade = velha.get();

        entidade.setQuitada(nova.isQuitada());
        entidade.setMensagem(nova.getMensagem());
        entidade.setArrecadado(nova.getArrecadado());
        entidade.setDataVencimento(nova.getDataVencimento());
        entidade.setNome(nova.getNome());
        entidade.setTotal(nova.getTotal());

        caixinhaRepository.save(entidade);
    }
}
