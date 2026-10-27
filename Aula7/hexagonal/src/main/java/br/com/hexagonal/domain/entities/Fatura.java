package br.com.hexagonal.domain.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import br.com.hexagonal.domain.exceptions.DomainException;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Fatura {

    public static final String STATUS_PENDENTE = "PENDENTE";
    public static final String STATUS_BOLETO_EMITIDO = "BOLETO_EMITIDO";
    public static final String STATUS_NOTA_EMITIDA = "NOTA_EMITIDA";
    public static final String STATUS_EMAIL_ENVIADO = "EMAIL_ENVIADO";
    

    private UUID id;
    private Long pedidoId;
    private BigDecimal valor;
    private LocalDateTime emitidoEm;
    private String codigoBarras;
    private String status;
    private String rps;
    private String hashEmail;

    @Builder
    private Fatura(UUID id, Long pedidoId, BigDecimal valor, LocalDateTime emitidoEm,
                   String status, String rps, String hashEmail, String codigoBarras) {

        if (pedidoId == null || pedidoId <= 0) {
            throw new DomainException("A fatura precisa de um pedido!");
        }

        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new DomainException("O valor da fatura deve ser maior que zero!");
        }

        this.id = id;
        this.pedidoId = pedidoId;
        this.valor = valor;
        this.emitidoEm = emitidoEm;
        this.codigoBarras = codigoBarras;
        this.status = status;
        this.rps = rps;
        this.hashEmail = hashEmail;
    }

    public static Fatura criar(Long pedidoId, BigDecimal valor) {
        return Fatura.builder()
                .id(UUID.randomUUID())
                .pedidoId(pedidoId)
                .valor(valor)
                .emitidoEm(LocalDateTime.now())
                .status(STATUS_PENDENTE)
                .build();
    }

    public void inserirBoleto(String codigoBarras) {
        if (codigoBarras == null || codigoBarras.isBlank()) {
            throw new DomainException("O código de barras do boleto é obrigatório!");
        }
        this.codigoBarras = codigoBarras;
        this.status = STATUS_BOLETO_EMITIDO;
    }

    public void inserirRps(String rps) {
        if (rps == null || rps.isBlank()) {
            throw new DomainException("O RPS da nota é obrigatório!");
        }
        this.rps = rps;
        this.status = STATUS_NOTA_EMITIDA;
    }

    public void inserirHashEmail(String hashEmail) {
        if (hashEmail == null || hashEmail.isBlank()) {
            throw new DomainException("O hash do email é obrigatório!");
        }
        this.hashEmail = hashEmail;
        this.status = STATUS_EMAIL_ENVIADO;
    }
}