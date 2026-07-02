package com.bancorm.transferencia.aplicacao;

import com.bancorm.transferencia.dominio.Conta;
import com.bancorm.transferencia.dominio.RegraTransferencia;
import com.bancorm.transferencia.portas.ContaRepository;
import com.bancorm.transferencia.portas.TransferirDinheiroUseCase;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransferirDinheiroService implements TransferirDinheiroUseCase {

    private final ContaRepository contaRepository;

    private final RegraTransferencia regraTransferencia = new RegraTransferencia();

    public TransferirDinheiroService(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    @Override
    public void transferir(String idContaOrigem, String idContaDestino, BigDecimal valor) {

        Conta Origem = contaRepository.buscarPorId(idContaOrigem)
                .orElseThrow(() -> new IllegalArgumentException("Conta de origem não encontrada."));

        Conta Destino = contaRepository.buscarPorId(idContaDestino)
                .orElseThrow(() -> new IllegalArgumentException("Conta de destino não encontrada."));

        regraTransferencia.executar(Origem, Destino, valor);

        contaRepository.salvar(Origem);
        contaRepository.salvar(Destino);

    }
}
