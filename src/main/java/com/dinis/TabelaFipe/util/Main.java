package com.dinis.TabelaFipe.util;

import com.dinis.TabelaFipe.domin.DataFipe;
import com.dinis.TabelaFipe.domin.DataModels;
import com.dinis.TabelaFipe.service.ApiService;
import com.dinis.TabelaFipe.service.DataConverter;

import java.util.*;

public class Main {

    private String optionTypes;
    private final String BASE_PATH = "https://parallelum.com.br/fipe/api/v1/";
    protected Scanner input = new Scanner(System.in);

        public void start(){

            System.out.println(Messages.MENU);
            System.out.println(Messages.TYP_MENU);

           while (true) {

               String optionTypes = input.nextLine();

               String[] types = {"carros", "motos", "caminhoes"};

               String finalOptionTypes = optionTypes;
               optionTypes = Arrays.stream(types)
                       .map(String::toLowerCase)
                       .filter(type -> type.startsWith(finalOptionTypes.toLowerCase()))
                       .findFirst()
                       .orElse(null);

               if(optionTypes != null){
                   System.out.println(optionTypes);
                   String path =  BASE_PATH + optionTypes + "/marcas";
                   ApiService api = new ApiService();
                   var json = api.fetchDataFromApi(path);
                   DataConverter converter = new DataConverter();
                   var models = converter.getList(json, DataFipe.class);
                   models.stream()
                           .sorted(Comparator.comparing(DataFipe::cod))
                           .forEach(System.out::println);

                   System.out.println(Messages.BRANDS_MENU);
                   String codBrands  = input.nextLine();

                   path = path + "/" + codBrands + "/modelos";
                   json = api.fetchDataFromApi(path);
                   var modelList = converter.fetchData(json, DataModels.class);
                   modelList.modesls().stream()
                           .sorted(Comparator.comparing(DataFipe::cod))
                           .forEach(System.out::println);

                   break;
               }else{
                   System.out.println(Messages.ERROR_TYPES);
                   System.out.println(Messages.TYP_MENU);
               }
           }
        }
}


