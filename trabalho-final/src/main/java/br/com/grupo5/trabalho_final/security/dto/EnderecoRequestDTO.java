package br.com.grupo5.trabalho_final.security.dto;

public class EnderecoRequestDTO {
	
	private String complemento;
    private Integer numero;
    private String cep;
    
    public String getComplemento() {
        return complemento;
    }
    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
    public Integer getNumero() {
        return numero;
    }
    public void setNumero(Integer numero) {
        this.numero = numero;
    }
    public String getCep() {
        return cep;
    }
    public void setCep(String cep) {
        this.cep = cep;
    }

}
