package br.com.exemplo.spring.data.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.exemplo.spring.data.entities.Cargo;
import br.com.exemplo.spring.data.entities.Funcionario;
import br.com.exemplo.spring.data.entities.UnidadeDeTrabalho;
import br.com.exemplo.spring.data.repository.CargoRepository;
import br.com.exemplo.spring.data.repository.FuncionarioRepository;
import br.com.exemplo.spring.data.repository.UnidadeDeTrabalhoRepository;

@Service
public class FuncionarioService {
	
	private Boolean system = true;
	
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	@Autowired
	private FuncionarioRepository funcRepository;
	
	@Autowired
	private CargoRepository cargoRepository;
	
	@Autowired
	private UnidadeDeTrabalhoRepository unRepository;


	public void inicial(Scanner sc) {
				
		while(system) {
			System.out.println();
			System.out.println("0 - Voltar para o menu anterior");
			System.out.println("1 - Salvar");
			System.out.println("2 - Atualizar");
			System.out.println("3 - Listar todos");
			System.out.println("4 - Deletar");
			
			int action = sc.nextInt();
			switch(action) {
			case 1:
				salvar(sc);
				break;
			case 2:
				atualizar(sc);
				break;
			case 3:
				visualizar(sc);
				break;
			case 4:
				deletar(sc);
				break;
			default:
				system = false;
				break;			
			}	
		}		
	}
	
	
	public void salvar(Scanner sc) {
		System.out.println();
		System.out.print("Informe o nome: "); sc.next();
		String nome = sc.next(); 
		System.out.print("Informe o CPF: ");
		String cpf = sc.next();
		System.out.print("Informe o salário: ");
		Double salario = sc.nextDouble();
		System.out.print("Data da contratação: ");
		String data = sc.next();
		System.out.print("Digite o Id do Cargo: ");
		Integer cargoId = sc.nextInt();
		
		List<UnidadeDeTrabalho> unidades = unidade(sc);
		
		Funcionario func = new Funcionario(nome, cpf, salario, LocalDate.parse(data, formatter));
		Optional<Cargo> cargo = cargoRepository.findById(cargoId);
		func.setCargos(cargo.get());
		func.setUnidadesDeTrabalho(unidades);		
		
		funcRepository.save(func);		
		System.out.println("Funcionário cadastrado com sucesso!");
	}
	
	private List<UnidadeDeTrabalho> unidade(Scanner sc) {
		Boolean isTrue = true;
		List<UnidadeDeTrabalho> unidades = new ArrayList<>();
		
		while(isTrue) {
			System.out.println("Digite a unidadeId (Para sair digite 0)");
			Integer unidadeId = sc.nextInt();
			
			if(unidadeId != 0) {
				Optional<UnidadeDeTrabalho> unidade = unRepository.findById(unidadeId);
				unidades.add(unidade.get());
			}else {
				isTrue = false;
			}
		}		
		return unidades;
	}


	private void atualizar(Scanner sc) {
		System.out.print("Digite o Id a ser atualizado: ");
		Integer id = sc.nextInt();
		System.out.print("Informe o Nome: ");
		String nome = sc.next();
		System.out.print("Informe o CPF: ");
		String cpf = sc.next();
		System.out.print("Informe o salário: ");
		Double salario = sc.nextDouble();
		System.out.print("Data da contratação: ");
		String data = sc.next();
		System.out.print("Cargo: ");
		Integer cargoId = sc.nextInt();
		
		Funcionario func = new Funcionario();
		func.setId(id);
		func.setNome(nome);
		func.setCpf(cpf);
		func.setSalario(salario);
		func.setDataContratacao(LocalDate.parse(data, formatter));
		Optional<Cargo> cargo = cargoRepository.findById(cargoId);
		func.setCargos(cargo.get());
		
		funcRepository.save(func);
		System.out.println(func.toString());
		System.out.println("Atualizado com sucesso!");		
	}	
	
	private void visualizar(Scanner sc) {
		System.out.print("Qual página você quer visualizar: ");
		Integer page = sc.nextInt();
		
		Pageable pageable = PageRequest.of(page, 2, Sort.by(Sort.Direction.ASC, "salario"));
		
		Page<Funcionario> funcionarios = funcRepository.findAll(pageable);
		System.out.println(funcionarios);
		System.out.println("Pagina atual " + funcionarios.getNumber());
		System.out.println("Total elemento " + funcionarios.getNumberOfElements());
		funcionarios.forEach(func -> System.out.println(func));
	}
	
	private void deletar(Scanner sc) {
		System.out.println("Digite o Id a ser deletado: ");
		int id = sc.nextInt();
		funcRepository.deleteById(id);
		System.out.println("Funcionário deletetado com sucesso!");		
	}	
}
