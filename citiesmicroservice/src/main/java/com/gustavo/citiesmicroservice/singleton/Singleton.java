package com.gustavo.citiesmicroservice.singleton;

import com.gustavo.citiesmicroservice.entity.City;
import java.util.List;

/**
 * Esta clase se encarga de que solo exista una instancia de los objetos
 * contenidos en ella. En este ejemplo la utilizaremos para emular la base de
 * datos.
 *
 * @author Gustavo Cardenas Alba.
 * @version 24/08/2023/A
 */
public class Singleton {

    private static Singleton instance;
    private List<City> cities;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

}
