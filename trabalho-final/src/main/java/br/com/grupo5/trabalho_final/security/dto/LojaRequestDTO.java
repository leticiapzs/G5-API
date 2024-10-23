package br.com.grupo5.trabalho_final.security.dto;

public class LojaRequestDTO {

	private String cnpj;
	private String nomeFantasia;
	private String username;
	private String email;
	private String password;
	private String complemento;
	private Integer numero;
	private String cep;
	
	
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getNomeFantasia() {
		return nomeFantasia;
	}
	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
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

	public LojaRequestDTO() {
		
	}

    public EnderecoRequestDTO toEnderecoDTO() {
    return new EnderecoRequestDTO(this.cep, this.complemento, this.numero);
    		
    }
    public SignupRequestDTO toSingupDTO() {
    return new SignupRequestDTO(this.username, this.password, this.email);
    }
}

