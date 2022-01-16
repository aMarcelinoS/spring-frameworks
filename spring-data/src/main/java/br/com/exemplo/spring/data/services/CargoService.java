package br.com.exemplo.spring.data.services;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.exemplo.spring.data.entities.Cargo;
import br.com.exemplo.spring.data.repository.CargoRepository;

@Service
public class CargoService {
	private Boolean system = true;
	
	@Autowired
	private CargoRepository repository;

	
	public void inicial(Scanner sc) {
		
		while (system) {
			System.out.println("");
			System.out.println("0 - Votar para menu anterior");
			System.out.println("1 - Salvar");
			System.out.println("2 - Atualizar");
			System.out.println("3 - Visualizar todos");
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
				visualizar();
				break;
			case 4:
				deleteById(sc);
				break;
			default:
			system = false;
			}
		}
		
	}
	
		
	public void salvar(Scanner sc) {
		System.out.print("Descrição do cargo: ");
		String descricao = sc.next();
		
		Cargo cargo = new Cargo(descricao);
		repository.save(cargo);	
		System.out.println("Cadastrado com sucesso!");
	}

	public void atualizar(Scanner sc) {
		System.out.print("Digite o Id a ser atualizado: ");
		Integer id = sc.nextInt();
		System.out.print("Digite a nova descrição: ");
		String descricao = sc.next();
		
		Cargo cargo = new Cargo();
		cargo.setId(id);
		cargo.setDescricao(descricao);
		repository.save(cargo);		
		System.out.println("Atualizado com sucesso!");
	}	
	
	public void visualizar() {
		Iterable<Cargo> cargos = repository.findAll();		
		cargos.forEach(cargo-> System.out.println(cargo));
	}
	
	public void deleteById(Scanner sc) {
		System.out.println("Digite o Id a ser deletado: ");
		int id = sc.nextInt();
		repository.deleteById(id);
		System.out.println("Id " + id + " Deletado com sucesso!");
		System.out.println();
		visualizar();		
	}	
}
