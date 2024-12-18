package com.lopeshenriquee.TabelaFipe.principal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.lopeshenriquee.TabelaFipe.service.ConsumptionAPI;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.Normalizer;
import java.util.Scanner;

public class Principal {
    Scanner scan = new Scanner(System.in);

    private final String URL_BASE = "https://parallelum.com.br/fipe/api/v1";

    private ConsumptionAPI consumptionAPI = new ConsumptionAPI();

    public void showMenu() {
        String address;
        String option;

        do {
            var menu = """
                    *** Tipo de Veículo ***
                    Carro - 1
                    Moto - 1
                    Caminhão- 1
                    
                    Digite uma das opções para consulta:
                    """;


            System.out.println(menu);
            option = normalizeInput(scan.nextLine());

            if (option.equals("carro") || option.equals("1")) {
                address = URL_BASE + "/carros/marcas";
                break;
            } else if (option.equals("moto") || option.equals("2")) {
                address = URL_BASE + "/motos/marcas";
                break;
            } else if (option.equals("caminhão") || option.equals("3")) {
                address = URL_BASE + "/caminhoes/marcas";
                break;
            } else {
                System.out.println("Opção inválida.");
            }
        } while (true);

        var json = consumptionAPI.getData(address);
        formattedJson(json);
    }

    // Metod para Remove acentos e espaços extras e deixa tudo em minúsculas
    private String normalizeInput(String input) {
        return Normalizer.normalize(input, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "") // Remove acentos
                .trim() // Remove espaços extras
                .toLowerCase(); // Converte para minúsculas
    }
    // Metod para formatar o JSON
    private void formattedJson(String json){
        try {
            ObjectMapper mapper = new ObjectMapper();
            Object jsonObject = mapper.readValue(json, Object.class);
            ObjectWriter writer = mapper.writerWithDefaultPrettyPrinter();
            String formattedJson = writer.writeValueAsString(jsonObject);

            System.out.println(formattedJson);
        } catch (Exception e) {
            System.out.println("Erro ao formatar JSON: " + e.getMessage());
        }
    }

}
