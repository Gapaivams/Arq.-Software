package br.com.hexagonal.infra.adapters.out.soap;

import java.util.UUID;

import org.springframework.stereotype.Component;

import br.com.hexagonal.domain.entities.Fatura;
import br.com.hexagonal.domain.ports.out.soap.NotaSoap;

@Component
public class NotaSoapImp implements NotaSoap {

    private static final String PREFIXO_RPS = "RPS";

    /**
     * Emissão SIMULADA: monta o número do RPS
     * (prefixo + pedido + sequência única).
     * TODO: integrar com o web service SOAP da prefeitura/provedor real.
     */
    @Override
    public String emitirNota(Fatura fatura) {
        String sequencia = UUID.randomUUID().toString().substring(0, 8).toUpperCase();

        return PREFIXO_RPS
                + String.format("%010d", fatura.getPedidoId())
                + sequencia;
    }
}