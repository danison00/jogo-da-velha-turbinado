package com.dan.jogodavelhaturbinado2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.dan.jogodavelhaturbinado2.model.entity.Test;
import com.fasterxml.jackson.core.JsonProcessingException;


@SpringBootApplication
public class JogoDaVelhaTurbinado2Application {

	public static void main(String[] args) {
		
		// try {
		// 	new Test().run();
		// } catch (JsonProcessingException e) {
		// 	// TODO Auto-generated catch block
		// 	e.printStackTrace();
		// }
		SpringApplication.run(JogoDaVelhaTurbinado2Application.class, args);
	}

}
