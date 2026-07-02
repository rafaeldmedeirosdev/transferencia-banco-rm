package com.bancorm.transferencia.portas;

import java.math.BigDecimal;

public interface NotificarTransferenciaPort {

    void notificar (String idContaOrigem, String idContaDestino, BigDecimal valor);
}
