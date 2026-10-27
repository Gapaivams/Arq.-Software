package br.com.hexagonal.infra.adapters.out.persistence.jpa;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import br.com.hexagonal.domain.entities.Fatura;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "fatura")
public class FaturaEntityJpa {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "pedido_id", nullable = false)
    private Long pedidoId;

    @Column(name = "valor", nullable = false)
    private BigDecimal valor;

    @Column(name = "emitido_em")
    private LocalDateTime emitidoEm;

    @Column(name = "codigo_barras")
    private String codigoBarras;

    @Column(name = "status")
    private String status;

    @Column(name = "rps")
    private String rps;

    @Column(name = "hash_email")
    private String hashEmail;

    public static FaturaEntityJpa doDominio(Fatura fatura) {
        return FaturaEntityJpa.builder()
                .id(fatura.getId())
                .pedidoId(fatura.getPedidoId())
                .valor(fatura.getValor())
                .emitidoEm(fatura.getEmitidoEm())
                .codigoBarras(fatura.getCodigoBarras())
                .status(fatura.getStatus())
                .rps(fatura.getRps())
                .hashEmail(fatura.getHashEmail())
                .build();
    }

    public Fatura paraDominio() {
        return Fatura.builder()
                .id(id)
                .pedidoId(pedidoId)
                .valor(valor)
                .emitidoEm(emitidoEm)
                .codigoBarras(codigoBarras)
                .status(status)
                .rps(rps)
                .hashEmail(hashEmail)
                .build();
    }
}