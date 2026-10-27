package br.com.hexagonal.infra.adapters.in.rest.dtos;

import java.math.BigDecimal;

public record FaturaPostResponse(String faturaId, Long pedidoId, BigDecimal valor) {
}