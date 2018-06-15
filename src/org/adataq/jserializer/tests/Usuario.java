package org.adataq.jserializer.tests;

import java.util.Date;
import java.util.List;

import org.adataq.jserializer.SerializationAccess;
import org.adataq.jserializer.SerializationAttribute;

public class Usuario {
	
	private Long id;
	
	@SerializationAttribute(name = "nomeDoCara")
	private String nome;
	
	private Double salario;
	
	private boolean ativo = false;
	
	private Date dataDeNascimento;
	
	private Endereco enderecoPrincipal;
	
	private List<Endereco> enderecos;
	
	@SerializationAccess(readable = false)
	private Usuario[] associados;
	
	private Usuario chefe;
	
	private Tipos tipo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public Date getDataDeNascimento() {
		return dataDeNascimento;
	}

	public void setDataDeNascimento(Date dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public Usuario[] getAssociados() {
		return associados;
	}

	public void setAssociados(Usuario[] associados) {
		this.associados = associados;
	}
	
	public Endereco getEnderecoPrincipal() {
		return enderecoPrincipal;
	}
	
	public void setEnderecoPrincipal(Endereco enderecoPrincipal) {
		this.enderecoPrincipal = enderecoPrincipal;
	}
	
	public Tipos getTipo() {
		return tipo;
	}

	public void setTipo(Tipos tipo) {
		this.tipo = tipo;
	}

	public Usuario getChefe() {
		return chefe;
	}
	
	public void setChefe(Usuario chefe) {
		this.chefe = chefe;
	}
}
