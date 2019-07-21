package com.autumnutil.ditio.util;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReflectionUtil {


    public static List<Method> getGetters(Class aClass) {
        return Stream.of(aClass.getMethods()).filter(ReflectionUtil::isGetter).collect(Collectors.toList());
    }

    public static Optional<Method> getGetter(Class aClass, String fieldName) {
        return getGetters(aClass).stream().filter(method -> method.getName().substring(3).equals(fieldName)).findFirst();
    }

    public static List<Method> getSetters(Class aClass) {
        return Stream.of(aClass.getMethods()).filter(ReflectionUtil::isSetter).collect(Collectors.toList());
    }

    public static Optional<Method> getSetter(final Class aClass, final String fieldName) {
        return getSetters(aClass).stream().filter(method -> method.getName().substring(3).equals(fieldName)).findFirst();
    }

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
