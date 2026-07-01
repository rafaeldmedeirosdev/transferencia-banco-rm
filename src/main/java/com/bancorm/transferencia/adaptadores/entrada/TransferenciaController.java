package com.bancorm.transferencia.adaptadores.entrada;

import com.bancorm.transferencia.portas.TransferirDinheiroUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/transferencias")
public class TransferenciaController {

    private final TransferirDinheiroUseCase transferirDinheiroUseCase;

    public TransferenciaController(TransferirDinheiroUseCase transferirDinheiroUseCase) {
        this.transferirDinheiroUseCase = transferirDinheiroUseCase;

    }

    @PostMapping
    public ResponseEntity<String> transferir(@RequestBody TransferenciaRequest request){

        transferirDinheiroUseCase.transferir(
                request.contaOrigem(),
                request.contaDestino(),
                request.valor()
        );

        return ResponseEntity.ok().body("Transferência iniciada com sucesso!");
    }

    record TransferenciaRequest(String contaOrigem, String contaDestino, BigDecimal valor){}
}
