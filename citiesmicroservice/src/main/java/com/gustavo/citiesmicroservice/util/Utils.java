package com.gustavo.citiesmicroservice.util;


import com.gustavo.citiesmicroservice.entity.City;
import com.gustavo.citiesmicroservice.parser.Parser;
import static com.gustavo.citiesmicroservice.util.ConstantValues.ARRAY;
import static com.gustavo.citiesmicroservice.util.ConstantValues.COLLECTION;
import static com.gustavo.citiesmicroservice.util.ConstantValues.MAP;
import static com.gustavo.citiesmicroservice.util.ConstantValues.NATIVE;
import static com.gustavo.citiesmicroservice.util.ConstantValues.OBJECT;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Esta clase se encarga de convertir objetos POJO(Plain Java Objects) a formato
 * JSON.
 *
 * @author Gustavo Cardenas Alba.
 * @version 24/08/2023/A
 */
public class Utils {

    /*
    * Metodo que determina el tipo de instacia segun la clase como parametro.
    * @param type: Parámetro tipo Class del objeto a determinar.
    * @return String con la instacia del objeto.
     */
    public static String typeInstance(Class type) {
        String instance = null;

        try {
            if (type.isArray()) {
                instance = ARRAY;
            } else if (Collection.class.isAssignableFrom(type)) {
                instance = COLLECTION;
            } else if (Map.class.isAssignableFrom(type)) {
                instance = MAP;
            } else if (type.isPrimitive() || isObjectDataType(type)) {
                instance = NATIVE;
            } else {
                instance = OBJECT;
            }
        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
        }

        return instance;
    }

    /*
    * Metodo que determina si una clase pertenece es de tipo nativo.
    * @param type: Parámetro tipo Class del objeto a determinar.
    * @return Objeto boolean que detrmina si la clase es nativa.
     */
    public static boolean isObjectDataType(Class type) {
        boolean isObjectDataType = false;
        while (type.getSuperclass() != null) {
            if (Comparable.class.isAssignableFrom(type)) {
                isObjectDataType = true;
                break;
            }
            type = type.getSuperclass();
        }
        return isObjectDataType;
    }

    /*
    * Metodo que determina si un objeto esta vacio segun el tipo de instancia.
    * @param obj: Objeto a determinar.
    * @param type: Parámetro tipo Class del objeto a determinar.
    * @return Objeto boolean que detrmina si el objeto esta vacio.
     */
    public static boolean isEmpty(Object obj, String type) {
        boolean isEmpty = false;
        if (obj != null) {
            switch (type) {
                case ARRAY:
                    Object[] objs = (Object[]) obj;
                    isEmpty = objs.length <= 0;
                    break;
                case COLLECTION:
                    Collection list = (Collection) obj;
                    isEmpty = list.isEmpty();
                    break;
                case MAP:
                    Map map = (Map) obj;
                    isEmpty = map.isEmpty();
                    break;
                default:
                    break;
            }
        }

        return isEmpty;
    }
    
    /*
    * Metodo que convierte un flujo de datos(archivo de datos origen) en una lista de objetos {City}.
    * @param fileInputStream : Objeto de clase InputStream que contiene los datos del archivo .tsv para su lectura y conversión.
    * @return Lista de objetos {City} que contenia el archivo .tsv.
     */
    public static ArrayList<City> convertInputStreamToCityArrayList(InputStream fileInputStream) throws IOException {
        //Inicializamos la vista.
        ArrayList<City> data = new ArrayList<>();

        //Declaracion de objetos para lectura de archivo.
        InputStreamReader isr = null;
        BufferedReader br = null;
        Stream<String> streamLines = null;
        try {
            //Convertimos el flujo del archivo en lineas.
            isr = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
            br = new BufferedReader(isr);
            streamLines = br.lines();

            //Saltamos la primer linea para evitar los encabezados.
            streamLines = streamLines.skip(1);

            //Buscamos en cada linea, sepraramos los datos y creamos el objeto {City}.
            streamLines.forEach(line -> {
                String[] lineItems = line.split("\t"); //splitting the line and adding its items in String[]

                try {
                    data.add(new City(
                            Parser.parse(lineItems[0], Integer.class),
                            lineItems[1],
                            lineItems[2],
                            lineItems[3],
                            Parser.parse(lineItems[4], Double.class),
                            Parser.parse(lineItems[5], Double.class),
                            Parser.parse(lineItems[6], Character.class),
                            lineItems[7],
                            lineItems[8],
                            lineItems[9],
                            lineItems[10],
                            lineItems[11],
                            lineItems[12],
                            lineItems[13],
                            Parser.parse(lineItems[14], Long.class),
                            Parser.parse(lineItems[15], Integer.class),
                            Parser.parse(lineItems[16], Integer.class),
                            lineItems[17],
                            Parser.parse(lineItems[18], java.util.Date.class)
                    ));
                } catch (Exception e) {
                    System.err.println("ERROR: Ocurrio un error durante la lectura del id[" + lineItems[0] + "]");
                    System.err.println(e.getMessage());
                }
            });
        } catch (Exception e) {
            System.out.println("ERROR: Ha ocurrido un error en el proceso de lectura del archivo de datos.");
        } finally {
            if (isr != null) {
                isr.close();
            }
            if (br != null) {
                br.close();
            }
            if (streamLines != null) {
                streamLines.close();
            }
        }
        return data;
    }
}
