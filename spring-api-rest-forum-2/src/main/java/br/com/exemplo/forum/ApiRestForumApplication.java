//Spring Boot API Rest: Segurança da API, Cache e Monitoramento

package br.com.exemplo.forum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
@EnableCaching
public class ApiRestForumApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiRestForumApplication.class, args);
	}

}
