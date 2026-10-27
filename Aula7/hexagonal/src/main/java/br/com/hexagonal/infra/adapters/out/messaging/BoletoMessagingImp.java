package br.com.hexagonal.infra.adapters.out.messaging;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.stereotype.Component;

import br.com.hexagonal.domain.entities.Fatura;
import br.com.hexagonal.domain.ports.out.messaging.BoletoMessaging;

@Component
public class BoletoMessagingImp implements BoletoMessaging {

    private static final String BANCO = "237";
    private static final String MOEDA = "9";

    /**
     * Emissão SIMULADA: monta um código de 44 dígitos
     * (banco + moeda + valor em centavos + pedido + sequência única).
     * TODO: integrar com o provedor/banco real.
     */
    @Override
    public String emitirBoleto(Fatura fatura) {
        long centavos = fatura.getValor().multiply(BigDecimal.valueOf(100)).longValue();
        String sequencia = String.format("%020d",
                Math.abs(UUID.randomUUID().getMostSignificantBits() % 100_000_000_000_000_000L));

        return BANCO
                + MOEDA
                + String.format("%010d", centavos)
                + String.format("%010d", fatura.getPedidoId())
                + sequencia;
    }
}