package com.capibyte.acervo.apresentacao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
		"com.capibyte.acervo.apresentacao",
		"com.capibyte.acervo.dominio",
		"com.capibyte.acervo.infraestrutura" // <- Isso é essencial
})
public class ApresentacaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApresentacaoApplication.class, args);
	}

}
