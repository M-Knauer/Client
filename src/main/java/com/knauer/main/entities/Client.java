package com.knauer.main.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_client")
public class Client implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 30)
	private String nome;
	
	@Column(columnDefinition = "CHAR(11)")
	private String cpf;
	
	private Double income;
	
	@Column(name = "birth_day",
			columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant birthDat;
	
	private Integer children;
	
	public Client() {
		
	}

	public Client(Long id, String nome, String cpf, Double income, Instant birthDat, Integer children) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.income = income;
		this.birthDat = birthDat;
		this.children = children;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Double getIncome() {
		return income;
	}

	public void setIncome(Double income) {
		this.income = income;
	}

	public Instant getBirthDat() {
		return birthDat;
	}

	public void setBirthDat(Instant birthDat) {
		this.birthDat = birthDat;
	}

	public Integer getChildren() {
		return children;
	}

	public void setChildren(Integer children) {
		this.children = children;
	}

	public Long getId() {
		return id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		return Objects.equals(id, other.id);
	}

}
