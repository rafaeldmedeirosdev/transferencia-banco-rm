package com.bancorm.transferencia.adaptadores.saida;

import com.bancorm.transferencia.portas.NotificarTransferenciaPort;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class NotificacaoKafkaAdapter implements NotificarTransferenciaPort {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public NotificacaoKafkaAdapter(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void notificar(String idContaOrigem, String idContaDestino, BigDecimal valor) {
        String mensagemJson = String.format(
                "{\"contaOrigem\": \"%s\", \"contaDestino\": \"%s\",\"valor\": %s}",
                idContaOrigem, idContaDestino, valor
        );

        kafkaTemplate.send("transferencias-topico", mensagemJson);

        System.out.println("🚀 PRODUTOR: A mensagem de transferência foi enviada para o Kafka!");}
}
