package com.bancorm.transferencia.portas;

import java.math.BigDecimal;

public interface TransferirDinheiroUseCase {
    void transferir(String idContaOrigem, String idContaDestino, BigDecimal valor);
}
