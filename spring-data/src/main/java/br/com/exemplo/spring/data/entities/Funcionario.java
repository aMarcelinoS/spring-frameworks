package br.com.exemplo.spring.data.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "funcionarios")
public class Funcionario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private String cpf;
	private Double salario;
	@Column(name = "data_de_contratacao")
	private LocalDate dataContratacao;
	
	@ManyToOne
	@JoinColumn(name = "cargo_id", nullable = false)
	private Cargo cargos;
	
	@Fetch(FetchMode.SELECT)
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "funcionarios_unidades", joinColumns = {@JoinColumn(name = "fk_funcionario")}, 
	inverseJoinColumns = {@JoinColumn(name = "fk_unidade")})
	private List<UnidadeDeTrabalho> unidadesDeTrabalho = new ArrayList<>();
	
	
	public Funcionario() {
	}

	public Funcionario(String nome, String cpf, Double salario, LocalDate dataContratacao) {
		this.nome = nome;
		this.cpf = cpf;
		this.salario = salario;
		this.dataContratacao = dataContratacao;
	}

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	public LocalDate getDataContratacao() {
		return dataContratacao;
	}

	public void setDataContratacao(LocalDate dataContratacao) {
		this.dataContratacao = dataContratacao;
	}

	public Cargo getCargos() {
		return cargos;
	}

	public void setCargos(Cargo cargos) {
		this.cargos = cargos;
	}

	public List<UnidadeDeTrabalho> getUnidadesDeTrabalho() {
		return unidadesDeTrabalho;
	}
	
	public void setUnidadesDeTrabalho(List<UnidadeDeTrabalho> unidadesDeTrabalho) {
		this.unidadesDeTrabalho = unidadesDeTrabalho;
	}

	@Override
	public String toString() {
		return "Funcionario: " + "id:" + id + "| nome:" + nome + "| cpf:" + cpf + "| salario:" + salario
				+ "| dataContratacao:" + dataContratacao + "| cargos:" + cargos.getDescricao() +"]";
	}
	
	
}
