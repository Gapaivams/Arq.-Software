package br.com.hexagonal.infra.adapters.out.smtp;

import java.util.UUID;

import org.springframework.stereotype.Component;

import br.com.hexagonal.domain.entities.Fatura;
import br.com.hexagonal.domain.ports.out.smtp.EmailSmtp;

@Component
public class EmailSmtpImp implements EmailSmtp {

    /**
     * Envio SIMULADO: devolve um hash único que identifica o email enviado
     * (boleto + nota da fatura).
     * TODO: integrar com o servidor SMTP real (JavaMailSender).
     */
    @Override
    public String enviarEmail(Fatura fatura) {
        return UUID.randomUUID().toString();
    }
}