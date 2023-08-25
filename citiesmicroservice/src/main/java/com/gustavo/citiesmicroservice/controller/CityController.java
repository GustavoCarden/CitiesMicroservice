package com.gustavo.citiesmicroservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.gustavo.citiesmicroservice.entity.City;
import com.gustavo.citiesmicroservice.formatter.json.JSONFactory;
import com.gustavo.citiesmicroservice.singleton.Singleton;
import com.gustavo.citiesmicroservice.util.Utils;
import static com.gustavo.citiesmicroservice.util.ConstantValues.COLLECTION;

@RestController
@RequestMapping("/suggestions")
public class CityController {

    @GetMapping()
    public String getSuggestions(@RequestParam("q") String q,
            @RequestParam(required = false) String latitude,
            @RequestParam(required = false) String longitude) {
        Object result = null;
        try {
            /*
             * Utilizamos este codigo para suplir la base de datos guardando los datos en
             * memoria.
             */
            if (Singleton.getInstance().getCities() == null) {
                InputStream inputStream = CityController.class.getResourceAsStream("../data/cities_canada-usa.tsv");
                Singleton.getInstance().setCities(Utils.convertInputStreamToCityArrayList(inputStream));
            }

            if (!Utils.isEmpty(Singleton.getInstance().getCities(), COLLECTION) && q != null) {
                List<City> auxList = new ArrayList<>();
                // Utilizamos SecureRandom ya que la clase Random arroja problemas de escaneo de código
                // como SonarQube entre otros.
                SecureRandom random = new SecureRandom();

                for (City city : Singleton.getInstance().getCities()) {
                    // Buscamos valores sin importar si son mayusculas o minusculas para maximizar
                    // la posibilidad de resultados.
                    if (city.getName().toLowerCase().contains(q.toLowerCase())) {
                        city.setScore(Float.valueOf(String.format("%.2f", random.nextFloat(0, 1))));
                        auxList.add(city);
                    }
                }

                // Ordenamos la lista de resultados por score.
                auxList.sort(new Comparator<City>() {
                    @Override
                    public int compare(City cityA, City cityB) {
                        return cityA.getScore().compareTo(cityB.getScore());
                    }
                });
                result = auxList;
            }
        } catch (Exception e) {
            result = new Error("ERROR: Ocurrio un error en la lectura de datos inciales." + e.getMessage());
        }
        JSONFactory factory = new JSONFactory().mapOnlyWithShowAnnotation();
        return factory.convertToJson(result);
    }

}
