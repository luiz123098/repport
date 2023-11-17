package com.gerador.formulario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.gerador.formulario.model.entity")
public class FormularioApplication {

	public static void main(String[] args) {
		SpringApplication.run(FormularioApplication.class, args);
	}

}
