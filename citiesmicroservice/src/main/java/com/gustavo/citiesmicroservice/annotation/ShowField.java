package com.gustavo.citiesmicroservice.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Interfase creada para la muestra de propiedades de objetos al momento de su
 * representacion en formato JSON.
 *
 * @author Gustavo Cardenas Alba.
 * @version 24/08/2023/A
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ShowField {

    public String key() default "";
}
