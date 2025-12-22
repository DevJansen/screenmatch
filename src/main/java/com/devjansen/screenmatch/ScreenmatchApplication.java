package com.devjansen.screenmatch;

import com.devjansen.screenmatch.model.DadosSerie;
import com.devjansen.screenmatch.service.ConsumoApp;
import com.devjansen.screenmatch.service.ConverteDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		ConsumoApp consumoApp = new ConsumoApp();
		var json = consumoApp.obterDados("http://www.omdbapi.com/?t=gilmore+girls&apikey=f7c802ea");

		ConverteDados conversor = new ConverteDados();
		DadosSerie dados = conversor.obterDados(json, DadosSerie.class);

		System.out.println(dados);



	}
}
