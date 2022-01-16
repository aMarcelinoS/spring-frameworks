package br.com.exemplo.spring.data.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.exemplo.spring.data.entities.Funcionario;
import br.com.exemplo.spring.data.entities.FuncionarioProjecao;
import br.com.exemplo.spring.data.repository.FuncionarioRepository;

@Service
public class RelatoriosService {
	private Boolean system = true;
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	@Autowired
	private FuncionarioRepository funcRepository;
	
	
	public void inicial(Scanner sc) {
	
		while(system) {
			System.out.println();
			System.out.println("0 - Voltar ao menu anterior");
			System.out.println("1 - Busca por nome");
			System.out.println("2 - Busca por nome, salario maior e data de contratação");
			System.out.println("3 - Busca por data de contratação");
			System.out.println("4 - Busca funcionário retornando id, nome, salario");
			
			int action = sc.nextInt();
			switch(action) {
			case 1:
				buscaPorNome(sc);
				break;
			case 2:
				buscaNomeSalarioContratacao(sc);
				break;
			case 3:
				buscaDataContratacao(sc);
			case 4:
				buscaFuncionarioSalario();
			default:
			}			
		}		
	}
	
	public void buscaPorNome(Scanner sc) {
		System.out.print("Digite o nome para busca: ");
		String nome = sc.next();
		System.out.println();
		List<Funcionario> list = funcRepository.findByNome(nome);
		list.forEach(System.out::println);		
	}
	
	public void buscaNomeSalarioContratacao(Scanner sc) {
		System.out.print("Digite o nome para busca: ");
		String nome = sc.next();
		
		System.out.print("Digite o salario para busca: ");
		Double salario = sc.nextDouble();
		
		System.out.print("Digite a data de contratação: ");
		String data = sc.next();
		
		List<Funcionario> list = funcRepository
				.findNomeSalarioMaiorDataContratacao(nome, salario, LocalDate.parse(data, formatter));
		System.out.println();
		list.forEach(System.out::println);
	}
	
	public void buscaDataContratacao(Scanner sc) {
		System.out.print("Digite a data de contratação: ");
		String data = sc.next();
		
		List<Funcionario> list = funcRepository.findDataContratacaoMaior(LocalDate.parse(data, formatter));
		System.out.println();
		list.forEach(System.out::println);
	}
	
	public void buscaFuncionarioSalario() {
		List<FuncionarioProjecao> list = funcRepository.findFuncionarioSalario();
		list.forEach(f -> System.out.println("Funcionario: id: " + f.getId() + "| Nome: " + f.getNome() + "| Salário: " + f.getSalario()));
	}	
}
