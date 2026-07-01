package com.bancorm.transferencia.dominio;


import java.math.BigDecimal;

public class RegraTransferencia {

    public void executar (Conta origem, Conta destino, BigDecimal valor){

        if (valor.compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalArgumentException("O valor de transferência deve ser maior que zero.");
        }
        origem.debitar(valor);
        destino.creditar(valor);
    }
}
