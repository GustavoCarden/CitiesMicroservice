package com.gustavo.citiesmicroservice.formatter.json;

import com.gustavo.citiesmicroservice.annotation.ShowField;
import com.gustavo.citiesmicroservice.util.ConstantValues;
import static com.gustavo.citiesmicroservice.util.ConstantValues.ARRAY;
import static com.gustavo.citiesmicroservice.util.ConstantValues.COLLECTION;
import static com.gustavo.citiesmicroservice.util.ConstantValues.COLLECTION_END;
import static com.gustavo.citiesmicroservice.util.ConstantValues.COLLECTION_START;
import static com.gustavo.citiesmicroservice.util.ConstantValues.EXCEPTION;
import static com.gustavo.citiesmicroservice.util.ConstantValues.MAP;
import static com.gustavo.citiesmicroservice.util.ConstantValues.NATIVE;
import static com.gustavo.citiesmicroservice.util.ConstantValues.OBJECT;
import static com.gustavo.citiesmicroservice.util.ConstantValues.OBJECT_END;
import static com.gustavo.citiesmicroservice.util.ConstantValues.OBJECT_START;
import com.gustavo.citiesmicroservice.util.Utils;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Esta clase se encarga de convertir objetos POJO(Plain Java Objects) a formato
 * JSON.
 *
 * @author Gustavo Cardenas Alba.
 * @version 24/08/2023/A
 */
public class JSONFormatter extends JSONFactory {

    /*
    * Metodo encargado de identificar y convertir el objeto de entrada en su representacion en formato JSON.
    * Este metodo es capaz de indentificar arreglos, colecciones, mapas, objetos, tipos datos nativos y primitivos.
    * @param o: Objeto a convertir.
    * @return Objeto tipo String que contiene el formato JSON.
     */
    public static String processObjectToData(Object o) {
        StringBuilder json = new StringBuilder();
        Class cls = o.getClass();
        switch (Utils.typeInstance(cls)) {
            case ARRAY:
                json.append(COLLECTION_START);
                switch (cls.getComponentType().getSimpleName()) {
                    case "short":
                        short[] shorts = (short[]) o;
                        for (int i = 0; i < shorts.length; i++) {
                            json.append(processObjectToData(shorts[i]));
                            if (i != shorts.length - 1) {
                                json.append(",");
                            }
                        }
                        break;
                    case "int":
                        int[] ints = (int[]) o;
                        for (int i = 0; i < ints.length; i++) {
                            json.append(processObjectToData(ints[i]));
                            if (i != ints.length - 1) {
                                json.append(",");
                            }
                        }
                        break;
                    case "float":
                        float[] floats = (float[]) o;
                        for (int i = 0; i < floats.length; i++) {
                            json.append(processObjectToData(floats[i]));
                            if (i != floats.length - 1) {
                                json.append(",");
                            }
                        }
                    case "double":
                        double[] doubles = (double[]) o;
                        for (int i = 0; i < doubles.length; i++) {
                            json.append(processObjectToData(doubles[i]));
                            if (i != doubles.length - 1) {
                                json.append(",");
                            }
                        }
                        break;
                    case "long":
                        long[] longs = (long[]) o;
                        for (int i = 0; i < longs.length; i++) {
                            json.append(processObjectToData(longs[i]));
                            if (i != longs.length - 1) {
                                json.append(",");
                            }
                        }
                        break;
                    case "char":
                        char[] chars = (char[]) o;
                        for (int i = 0; i < chars.length; i++) {
                            json.append(processObjectToData(chars[i]));
                            if (i != chars.length - 1) {
                                json.append(",");
                            }
                        }
                        break;
                    case "byte":
                        byte[] bytes = (byte[]) o;
                        for (int i = 0; i < bytes.length; i++) {
                            json.append(processObjectToData(bytes[i]));
                            if (i != bytes.length - 1) {
                                json.append(",");
                            }
                        }
                        break;
                    case "boolean":
                        boolean[] bools = (boolean[]) o;
                        for (int i = 0; i < bools.length; i++) {
                            json.append(processObjectToData(bools[i]));
                            if (i != bools.length - 1) {
                                json.append(",");
                            }
                        }
                        break;
                    default:
                        Object[] objects = (Object[]) o;
                        for (int i = 0; i < objects.length; i++) {
                            json.append(processObjectToData(objects[i]));
                            if (i != objects.length - 1) {
                                json.append(",");
                            }
                        }
                        break;
                }

                json.append(COLLECTION_END);
                break;

            case COLLECTION:
                json.append(COLLECTION_START);
                ArrayList list = (ArrayList) o;
                for (int i = 0; i < list.size(); i++) {
                    json.append(processObjectToData(list.get(i)));
                    if (i != list.size() - 1) {
                        json.append(",");
                    }
                }

                json.append(COLLECTION_END);
                break;

            case MAP:
                json.append(COLLECTION_START);
                LinkedHashMap<Object, Object> map = (LinkedHashMap) o;
                Object[] objects = map.entrySet().toArray();
                for (int i = 0; i < objects.length; i++) {
                    Map.Entry entry = (Map.Entry) objects[i];
                    json.append(processObjectToData(entry.getValue()));
                    if (i != objects.length - 1) {
                        json.append(",");
                    }
                }
                json.append(COLLECTION_END);
                break;

            case NATIVE:
            
                json.append(OBJECT_START);
                json.append("\"value\":");
                json.append(o);
                json.append(OBJECT_END);
                break;

            case OBJECT:
                json.append(OBJECT_START);
                json.append(processObjectToJson(o, cls));
                while (cls.getSuperclass() != null && !cls.getSuperclass().equals(Object.class)) {
                    String jsonAux = processObjectToJson(o, cls.getSuperclass());
                    if (!jsonAux.isEmpty() && json.length() > 1) {
                        json.append(",");
                    }
                    json.append(jsonAux);
                    cls = cls.getSuperclass();
                }
                json.append(OBJECT_END);
                break;

            default:
                System.err.println("Clase no reconocida...");
        }

        return json.toString();
    }

    /*
    * Metodo encargado de convertir un POJO en su representacion en formato JSON.
    * @param obj: Objeto a convertir.
    * @param cls: Clase a la que pertenece el POJO.
    * @return Objeto tipo String que contiene el formato JSON.
     */
    private static String processObjectToJson(Object obj, Class cls) {
        StringBuilder builder = new StringBuilder();
        Object value;
        try {
            Field[] fields = cls.getDeclaredFields();
            Field f;
            for (int i = 0; i < fields.length; i++) {
                f = fields[i];
                f.setAccessible(true);

                value = null;
                if (mapOnlyWhenAnnotationPresent) {
                    if (f.isAnnotationPresent(ShowField.class)) {
                        value = f.get(obj);
                    }
                } else {
                    value = f.get(obj);
                }

                if (value != null) {
                    Class typeClass = f.getType().equals(Object.class) ? value.getClass() : f.getType();
                    String typeIns = Utils.typeInstance(typeClass);

                    if (!Utils.isEmpty(value, typeIns)) {
                        if (i > 0 && !builder.toString().isEmpty()) {
                            builder.append(",");
                        }
                        builder.append("\"");
                        builder.append(f.getName());
                        builder.append("\"");
                        builder.append(":");

                        if (typeIns.equals(ARRAY) || typeIns.equals(COLLECTION) || typeIns.equals(MAP)
                                || typeIns.equals(OBJECT) || typeIns.equals(EXCEPTION)) {
                            builder.append(processObjectToData(value));
                        } else {
                            if (f.getType().equals(String.class)
                                    || f.getType().equals(Date.class)
                                    || f.getType().equals(java.sql.Date.class)
                                    || f.getType().equals(java.sql.Timestamp.class)) {
                                builder.append("\"");
                                if (f.getType().equals(Date.class)
                                        || f.getType().equals(java.sql.Date.class)
                                        || f.getType().equals(java.sql.Timestamp.class)) {
                                    SimpleDateFormat formater = new SimpleDateFormat(ConstantValues.DATE_PATTERN);
                                    value = formater.format(value);
                                }
                                builder.append(value);
                                builder.append("\"");
                            } else {
                                builder.append(value);
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
        }

        return builder.toString();
    }

}
