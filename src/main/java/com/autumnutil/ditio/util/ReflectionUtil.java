package com.autumnutil.ditio.util;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * The type Reflection util.
 */
public class ReflectionUtil {


    /**
     * Gets getters.
     *
     * @param aClass the a class
     * @return the getters
     */
    public static List<Method> getGetters(Class aClass) {
        return Stream.of(aClass.getMethods()).filter(ReflectionUtil::isGetter).collect(Collectors.toList());
    }

    /**
     * Gets getter.
     *
     * @param aClass    the a class
     * @param fieldName the field name
     * @return the getter
     */
    public static Optional<Method> getGetter(Class aClass, String fieldName) {
        return getGetters(aClass).stream().filter(method -> method.getName().substring(3).equals(fieldName)).findFirst();
    }

    /**
     * Gets setters.
     *
     * @param aClass the a class
     * @return the setters
     */
    public static List<Method> getSetters(Class aClass) {
        return Stream.of(aClass.getMethods()).filter(ReflectionUtil::isSetter).collect(Collectors.toList());
    }

    /**
     * Gets setter.
     *
     * @param aClass    the a class
     * @param fieldName the field name
     * @return the setter
     */
    public static Optional<Method> getSetter(final Class aClass, final String fieldName) {
        return getSetters(aClass).stream().filter(method -> method.getName().substring(3).equals(fieldName)).findFirst();
    }

    /**
     * Is getter boolean.
     *
     * @param method the method
     * @return the boolean
     */
    public static boolean isGetter(Method method) {
        if (!method.getName().startsWith("get")){
            return false;
        }
        if (method.getParameterTypes().length != 0) {
            return false;
        }
        if (void.class.equals(method.getReturnType())) {
            return false;
        }
        return true;
    }

    /**
     * Is setter boolean.
     *
     * @param method the method
     * @return the boolean
     */
    public static boolean isSetter(Method method) {
        if (!method.getName().startsWith("set")) {
            return false;
        }
        if (method.getParameterTypes().length != 1) {
            return false;
        }
        return true;
    }
}
