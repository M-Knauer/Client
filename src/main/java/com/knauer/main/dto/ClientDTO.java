package com.knauer.main.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import com.knauer.main.entities.Client;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public class ClientDTO implements Serializable {
		private static final long serialVersionUID = 1L;
		
		private Long id;
		
		@Size(max = 30, message = "Nome não pode ser maior do que 30 caracteres")
		@NotBlank(message = "Nome não pode ser vazio")
		private String name;
		
		@NotBlank(message = "CPF não pode ser vazio")
		@Size(max = 11, message = "CPF não pode ser do que 11 caracteres")
		private String cpf;
		
		@PositiveOrZero(message = "Valor não pode ser negativo")
		private Double income;
		
		@Past(message = "Data precisa ser do passado")
		private Instant birthDate;
		
		@PositiveOrZero(message = "Número não pode ser negativo")
		private Integer children;
		
		public ClientDTO() {
			
		}

		public ClientDTO(Long id, String name, String cpf, Double income, Instant birthDate, Integer children) {
			this.id = id;
			this.name = name;
			this.cpf = cpf;
			this.income = income;
			this.birthDate = birthDate;
			this.children = children;
		}
		
		public ClientDTO(Client entity) {
			this.id = entity.getId();
			this.name = entity.getName();
			this.cpf = entity.getCpf();
			this.income = entity.getIncome();
			this.birthDate = entity.getBirthDate();
			this.children = entity.getChildren();
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
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

		public Instant getBirthDate() {
			return birthDate;
		}

		public void setBirthDate(Instant birthDate) {
			this.birthDate = birthDate;
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
			return Objects.equals(id, other.getId());
		}

}
