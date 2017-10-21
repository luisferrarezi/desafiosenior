package br.com.desafiosenior.model;

public class Estado {
	private String Id;
	private String nome;
	private String qtdCidades;
	
	public Estado() {}
	
	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getQtdCidades() {
		return qtdCidades;
	}

	public void setQtdCidades(String qtdCidades) {
		this.qtdCidades = qtdCidades;
	}
}
