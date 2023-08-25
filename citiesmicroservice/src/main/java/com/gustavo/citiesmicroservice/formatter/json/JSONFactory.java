package com.gustavo.citiesmicroservice.formatter.json;

/**
 * Esta clase se encarga de configurar la instancia con la que se dara formato
 * JSON a los objetos.
 *
 * @author Gustavo Cardenas Alba.
 * @version 24/08/2023/A
 */
public class JSONFactory {

    public static boolean mapOnlyWhenAnnotationPresent;

    /*
    * Constructor por default
     */
    public JSONFactory() {
    }

    /*
    * Metodo que configura la instancia para solo tomar en cuenta los campos que tienen asignada la anotacion {ShowField}.
    * @return Objeto tipo JSONFactory con las configuraciones asignadas.
     */
    public JSONFactory mapOnlyWithShowAnnotation() {
        JSONFactory.mapOnlyWhenAnnotationPresent = true;
        return this;
    }

    /*
    * Metodo que convierte un objeto en su representacion JSON.
    * @param obj: Objeto a representar en JSON.
    * @param cls: Clase del objeto a representar.
    * @return Objeto String que contiene el objeto en formato JSON.
     */
    public String convertToJson(Object obj) {
        return JSONFormatter.processObjectToData(obj);
    }

}
