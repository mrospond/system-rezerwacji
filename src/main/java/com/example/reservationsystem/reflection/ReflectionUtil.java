package com.example.reservationsystem.reflection;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

public class ReflectionUtil {
    public static Map<String, Object> extractFields(Object object) {
        Class<?> clazz = object.getClass();
        Map<String, Object> result = new LinkedHashMap<>();
        try {
            for (Field declaredField : clazz.getDeclaredFields()) {
                declaredField.setAccessible(true);
                result.put(declaredField.getName(), declaredField.get(object));
            }
            return result;
        } catch (IllegalAccessException e) {
            throw new IllegalAccessUncheckedException(e);
        }
    }

    public static Object[] extractFieldsAsValuesArray(Object object) {
        Class<?> clazz = object.getClass();
        Field[] declaredFields = clazz.getDeclaredFields();
        Object[] result = new Object[declaredFields.length];
        try {
            for (int i = 0; i < declaredFields.length; i++) {
                Field field = declaredFields[i];
                field.setAccessible(true);
                result[i] = field.get(object);
            }
            return result;
        } catch (IllegalAccessException e) {
            throw new IllegalAccessUncheckedException(e);
        }
    }
}
