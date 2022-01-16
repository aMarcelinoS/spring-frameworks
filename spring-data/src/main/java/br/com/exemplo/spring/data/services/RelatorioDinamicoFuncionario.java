package br.com.exemplo.spring.data.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.exemplo.spring.data.entities.Funcionario;
import br.com.exemplo.spring.data.repository.FuncionarioRepository;
import br.com.exemplo.spring.data.specification.SpecificationFuncionario;

@Service
public class RelatorioDinamicoFuncionario {
	
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	@Autowired
	private FuncionarioRepository funcRepository;

	
	public void inicial(Scanner sc) {
		System.out.print("Digite o nome: ");
		String nome = sc.next();
		
		if(nome.equalsIgnoreCase("NULL")) {
			nome = null;
		}
		
		System.out.print("Digite o cpf: ");
		String cpf = sc.next();
		
		if(cpf.equalsIgnoreCase("NULL")) {
			cpf = null;
		}
		
		System.out.print("Digite o salário: ");
		Double salario = sc.nextDouble();
		
		if(salario == 0) {
			salario = null;
		}
		
		System.out.print("Digite a data de contratação: ");
		String data = sc.next();
		
		LocalDate dataContratacao;
		if(data.equalsIgnoreCase("NULL")) {
			dataContratacao = null;
		}else {
			dataContratacao = LocalDate.parse(data, formatter);
		}
		
		List<Funcionario> funcionarios = funcRepository.findAll(Specification
				.where(
						SpecificationFuncionario.nome(nome))
						.or(SpecificationFuncionario.cpf(cpf))
						.or(SpecificationFuncionario.salario(salario))
						.or(SpecificationFuncionario.dataContratacao(dataContratacao))				
				);
		System.out.println();
		funcionarios.forEach(System.out::println);			
	}	
}
