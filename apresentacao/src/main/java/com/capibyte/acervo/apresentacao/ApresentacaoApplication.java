package com.capibyte.acervo.apresentacao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.capibyte.acervo.infraestrutura.persistencia")
@SpringBootApplication(scanBasePackages = {
		"com.capibyte.acervo.apresentacao",
		"com.capibyte.acervo.dominio",
		"com.capibyte.acervo.infraestrutura"
})
@EntityScan(basePackages = {
		"com.capibyte.acervo.infraestrutura.persistencia.core.administracao.usuario",
		"com.capibyte.acervo.infraestrutura.persistencia.core.administracao.emprestimo",
		"com.capibyte.acervo.infraestrutura.persistencia.core.acervo.autor",
		"com.capibyte.acervo.infraestrutura.persistencia.core.acervo.exemplar",
		"com.capibyte.acervo.infraestrutura.persistencia.core.acervo.livro",
		"com.capibyte.acervo.infraestrutura.persistencia.core.opiniao",
})
public class ApresentacaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApresentacaoApplication.class, args);
	}

}
