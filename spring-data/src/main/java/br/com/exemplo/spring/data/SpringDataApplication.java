package br.com.exemplo.spring.data;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.exemplo.spring.data.services.CargoService;
import br.com.exemplo.spring.data.services.FuncionarioService;
import br.com.exemplo.spring.data.services.RelatorioDinamicoFuncionario;
import br.com.exemplo.spring.data.services.RelatoriosService;
import br.com.exemplo.spring.data.services.UnidadeDeTrabalhoService;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner{
	
	private Boolean system = true; 
	
	private final CargoService cargoService;
	private final FuncionarioService funcService;
	private final UnidadeDeTrabalhoService unTrabalhoService;
	private final RelatoriosService relatoriosService;
	private final RelatorioDinamicoFuncionario relatorioDinamicoFuncionario;
	
	public SpringDataApplication(CargoService cargoService, FuncionarioService funcService, UnidadeDeTrabalhoService unTrabalhoService, 
			RelatoriosService relatoriosService, RelatorioDinamicoFuncionario relatorioDinamicoFuncionario) {
		this.cargoService = cargoService;
		this.funcService = funcService;
		this.unTrabalhoService = unTrabalhoService;
		this.relatoriosService = relatoriosService;
		this.relatorioDinamicoFuncionario = relatorioDinamicoFuncionario;
	}


	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}

	
	@Override
	public void run(String... args) throws Exception {
		Scanner sc = new Scanner(System.in);	
		
		while(system) {
			System.out.println("Qual ação você quer executar?");
			System.out.println("0 - Sair");
			System.out.println("1 - Cargo");
			System.out.println("2 - Funcionarios");
			System.out.println("3 - Unidades de Trabalho");
			System.out.println("4 - Relatórios");
			System.out.println("5 - Relatórios dinâmico de funcionários");
			
			int action = sc.nextInt();
			switch(action) {
			case 1:
				cargoService.inicial(sc);
				break;
			case 2:
				funcService.inicial(sc);
				break;
			case 3:
				unTrabalhoService.inicial(sc);
				break;
			case 4:
				relatoriosService.inicial(sc);
				break;
			case 5:
				relatorioDinamicoFuncionario.inicial(sc);
				break;
			default:
				system = false;			
			}			
		}
		sc.close();
	}
}
