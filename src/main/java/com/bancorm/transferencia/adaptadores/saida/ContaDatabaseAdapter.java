package com.bancorm.transferencia.adaptadores.saida;

import com.bancorm.transferencia.dominio.Conta;
import com.bancorm.transferencia.portas.ContaRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class ContaDatabaseAdapter implements ContaRepository {

    private final Map<String, Conta> bancoDeDados = new HashMap();

    public ContaDatabaseAdapter() {
        bancoDeDados.put("123", new Conta("123", new java.math.BigDecimal("1000.00")));
        bancoDeDados.put("456", new Conta("456", new java.math.BigDecimal("500.00")));
    }

    @Override
    public Optional<Conta> buscarPorId(String id){
        return Optional.ofNullable(bancoDeDados.get(id));
    }

    @Override
    public void salvar(Conta conta) {
        bancoDeDados.put(conta.getId(), conta);
    }

}
