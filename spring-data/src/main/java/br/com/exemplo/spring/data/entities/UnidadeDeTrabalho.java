package br.com.exemplo.spring.data.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "un_trabalho")
public class UnidadeDeTrabalho {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private String endereco;
	
	@ManyToMany(mappedBy = "unidadesDeTrabalho", fetch = FetchType.EAGER)
	private List<Funcionario> funcionario = new ArrayList<>();

	public UnidadeDeTrabalho() {
	}

	public UnidadeDeTrabalho(String nome, String endereco) {
		this.nome = nome;
		this.endereco = endereco;		
	}
	
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public List<Funcionario> getFuncionario() {
		return funcionario;
	}	

	public void setFuncionario(List<Funcionario> funcionario) {
		this.funcionario = funcionario;
	}

	@Override
	public String toString() {
		return "UnidadeDeTrabalho [id=" + id + ", nome=" + nome + ", endereco=" + endereco + "]";
	}	
}
