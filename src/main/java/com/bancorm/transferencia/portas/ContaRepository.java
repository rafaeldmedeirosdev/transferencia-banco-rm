package com.bancorm.transferencia.portas;

import com.bancorm.transferencia.dominio.Conta;

import java.util.Optional;

public interface ContaRepository {
    Optional<Conta> buscarPorId(String id);
    void salvar(Conta conta);
}
