package com.devjansen.screenmatch.principal;

import com.devjansen.screenmatch.model.DadosEpisodio;
import com.devjansen.screenmatch.model.DadosSerie;
import com.devjansen.screenmatch.model.DadosTemporada;
import com.devjansen.screenmatch.service.ConsumoApi;
import com.devjansen.screenmatch.service.ConverteDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {

    private final String ENDERECO = "http://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=f7c802ea";

    private ConsumoApi consumoApi = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();


    Scanner sc = new Scanner(System.in);

    public void exibeMenu(){
        System.out.println("Digite o nome da série para busca");
        String nomeSerie = sc.nextLine();

        String json = consumoApi.obterDados(ENDERECO + nomeSerie.replace(" ","+") + API_KEY);
        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
        System.out.println(dados);

        List<DadosTemporada> temporadas = new ArrayList<>();
        for (int i = 1; i <= dados.totalTemporadas(); i++){
            json = consumoApi.obterDados(ENDERECO + nomeSerie.replace(" ","+")+"&season=" + i + API_KEY);
            DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
            temporadas.add(dadosTemporada);
        }

        //temporadas.forEach(System.out::println);

//        for(int i = 0; i < dados.totalTemporadas(); i++){
//            List<DadosEpisodio> episodioTemporada = temporadas.get(i).episodios();
//            for(int j =0; j < episodioTemporada.size(); j++){
//                System.out.println(episodioTemporada.get(j).titulo());
//            }
//
//        }

        temporadas.forEach(t -> t.episodios()
                .forEach(e -> System.out.println(e.titulo())));

    }



}
