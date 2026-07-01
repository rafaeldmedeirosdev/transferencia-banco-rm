package com.bancorm.transferencia.dominio;

import java.math.BigDecimal;

public class Conta {

    private final String id;
    private BigDecimal saldo;

    public Conta(String id, BigDecimal saldo) {
        this.id = id;
        this.saldo = saldo;
    }

    public void debitar(BigDecimal valor){
        if (this.saldo.compareTo(valor)<0){
            throw new IllegalArgumentException("Saldo insuficiente para transferência");
        }
        this.saldo = this.saldo.subtract(valor);
    }

    public void creditar(BigDecimal valor){
        this.saldo = this.saldo.add(valor);
    }

    public String getId() {
        return id;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }
}
