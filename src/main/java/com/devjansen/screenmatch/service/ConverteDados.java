package com.devjansen.screenmatch.service;

import com.devjansen.screenmatch.model.DadosSerie;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConverteDados implements IConverteDados {

    private ObjectMapper mapper = new ObjectMapper();


    @Override
    public <T> T obterDados(String json, Class<T> classe) {
        try {
            return mapper.readValue(json, classe);// recebe um json e tenta transforma em uma classe
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
