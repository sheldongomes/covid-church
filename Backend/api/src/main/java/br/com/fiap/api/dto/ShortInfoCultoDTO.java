package br.com.fiap.api.dto;

import br.com.fiap.api.model.ShortInfoPessoaModel;

public class ShortInfoCultoDTO {
	
	private String cpf;
	private String nome;
	
	public ShortInfoCultoDTO() {}
	
	public ShortInfoCultoDTO(ShortInfoPessoaModel model) {
		this.cpf = model.getCpf() == null ? null : model.getCpf().toString();
		this.nome = model.getNome();
	}

	
	public String getCpf() {
		return cpf;
	}
	public String getNome() {
		return nome;
	}

	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
}