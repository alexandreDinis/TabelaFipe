package com.dinis.TabelaFipe.util;

import com.dinis.TabelaFipe.domin.DataFipe;
import com.dinis.TabelaFipe.domin.DataModels;
import com.dinis.TabelaFipe.domin.Vehicle;
import com.dinis.TabelaFipe.service.ApiService;
import com.dinis.TabelaFipe.service.DataConverter;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private String optionTypes;
    private final String BASE_PATH = "https://parallelum.com.br/fipe/api/v1/";
    protected Scanner input = new Scanner(System.in);
    private final DataConverter converter = new DataConverter();

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
                   String path =  BASE_PATH + optionTypes + "/marcas";
                   ApiService api = new ApiService();
                   var json = api.fetchDataFromApi(path);
                   var models = converter.getList(json, DataFipe.class);

                   List<String> codList = models.stream()
                           .sorted(Comparator.comparing(DataFipe::name))
                           .peek(model -> System.out.println(model.cod() + ": " + model.name()))
                           .map(DataFipe::cod)
                           .toList();
                   System.out.println("1");

                   String codBrands;
                   do {
                       System.out.println(Messages.BRANDS_MENU);
                       codBrands = input.nextLine();

                       if (!codList.contains(codBrands)) {
                           System.out.println(Messages.COD_ERROR);
                       }
                   } while (!codList.contains(codBrands));

                   path = path + "/" + codBrands + "/modelos";
                   json = api.fetchDataFromApi(path);
                   var modelList = converter.fetchData(json, DataModels.class);
                   modelList.modesls().stream()
                                   .sorted(Comparator.comparing(DataFipe::name))
                                   .forEach(System.out::println);

                   System.out.println(Messages.FILTER_VEHICLE);
                   String filterVehicle = input.nextLine();

                    List<DataFipe> filteModels = modelList.modesls().stream()
                            .filter(m -> m.name().toLowerCase().contains(filterVehicle.toLowerCase()))
                            .toList();
                    filteModels.forEach(System.out::println);

                   System.out.println(Messages.MODELS_MENU);
                   String modelCod = input.nextLine();
                   path = path + "/" + modelCod + "/anos";
                   json = api.fetchDataFromApi(path);
                   List<DataFipe> years = converter.getList(json, DataFipe.class);
                   List<Vehicle> vehicles = new ArrayList<>();

                   for (int i = 0; i < years.size(); i++) {
                       String pathYears = path + "/" + years.get(i).cod();
                       json = api.fetchDataFromApi(pathYears);
                       Vehicle vehicle = converter.fetchData(json, Vehicle.class);
                       vehicles.add(vehicle);
                   }

                   System.out.println(Messages.VEHICLE_INFORMATION);
                   vehicles.forEach(System.out::println);

                   break;
               }else{
                   System.out.println(Messages.ERROR_TYPES);
                   System.out.println(Messages.TYP_MENU);
               }
           }
        }
}


