package br.com.grupo5.trabalho_final.security.dto;

public class PedidoResponseDTO {
  private Double valorTotal;
  private Integer clienteId;
  private boolean ativo;

  public PedidoResponseDTO(Double valorTotal, Integer clienteId, boolean ativo) {
    this.valorTotal = valorTotal;
    this.clienteId = clienteId;
    this.ativo = ativo;
  }

  public Double getValorTotal() {
    return valorTotal;
  }

  public void setValorTotal(Double valorTotal) {
    this.valorTotal = valorTotal;
  }

  public Integer getClienteId() {
    return clienteId;
  }

  public void setClienteId(Integer clienteId) {
    this.clienteId = clienteId;
  }

  public boolean isAtivo() {
    return ativo;
  }

  public void setAtivo(boolean ativo) {
    this.ativo = ativo;
  }
}