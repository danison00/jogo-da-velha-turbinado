package com.dan.jogodavelhaturbinado2.model.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Test {

    public void run() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        // Converter uma lista para uma string JSON
        List<String> l1 = List.of("item1", "item2", "item3");
        List<String> l2 = List.of("item1", "item2", "item3");
        List<String> l3 = List.of("item1", "item2", "item3");

        List<List<String>> l = new ArrayList<>() ;
        l.add(l1);
        l.add(l2);
        l.add(l3);

        String listaComoString = objectMapper.writeValueAsString(l);
        System.out.println("Lista como String: " + listaComoString);

        // Converter a string JSON de volta para uma lista
        List<List<String>> listaDeVolta = objectMapper.readValue(listaComoString, new TypeReference<List<List<String>>>() {
        });
        System.out.println("String como Lista: " + listaDeVolta);
    }
}
