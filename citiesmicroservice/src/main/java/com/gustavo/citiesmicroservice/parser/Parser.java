package com.gustavo.citiesmicroservice.parser;


import com.gustavo.citiesmicroservice.util.ConstantValues;
import java.text.SimpleDateFormat;

/**
 * Esta clase se encarga de realizar conversiones de la clase original a la
 * clase especificada.
 *
 * @author Gustavo Cardenas Alba.
 * @version 24/08/2023/A
 */
public class Parser {

    /*
    * Metodo encargado de convertir un dato de entrada en la clase especificada.
    * @param data: Dato original en su representacion String.
    * @param classToConvert: Clase destino a la cual se intentara realizar la conversión.
     */
    public static <T> T parse(String data, Class<T> classToConvert) throws Exception {
        T parserResult = null;

        try {
            if (data != null && !data.isEmpty()) {
                if (classToConvert.equals(Integer.class)) {
                    parserResult = (T) Integer.valueOf(data);
                } else if (classToConvert.equals(Double.class)) {
                    parserResult = (T) Double.valueOf(data);
                } else if (classToConvert.equals(Long.class)) {
                    parserResult = (T) Long.valueOf(data);
                } else if (classToConvert.equals(java.util.Date.class)) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat(ConstantValues.DATE_PATTERN);
                    parserResult = (T) dateFormat.parse(data);
                } else if (classToConvert.equals(Character.class)) {
                    parserResult = (T) ((Character) data.charAt(0));
                }
            }
        } catch (Exception e) {
            throw new Exception("ERROR: Ocurrio un error al convertir el dato[" + data + "] a la clase[" + classToConvert.getSimpleName() + "]");
        }

        return parserResult;
    }
}
