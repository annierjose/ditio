package com.autumnutil.ditio.service;

import com.autumnutil.ditio.dto.DtoModel;
import com.autumnutil.ditio.util.ReflectionUtil;
import org.modelmapper.ModelMapper;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class DtoMapperService {

    private ModelMapper modelMapper;

    public DtoMapperService() {
        this.modelMapper = new ModelMapper();
    }

    public <T, U> Object map(final U object, final Class<T> type) {
        if (object instanceof Collection) {
            List<Object> list = new ArrayList<>();
            ((Collection<?>) object).forEach(o -> list.add(map(o, type)));
            return list;
        } else {
            try {
                Object returnValue = type.newInstance();
                List<Method> setters = ReflectionUtil.getSetters(type);
                for (Method method : setters) {
                    Optional<Method> getterOptional = ReflectionUtil.getGetter(object.getClass(), method.getName().substring(3));
                    if (getterOptional.isPresent()) {
                        try {
                            Field declaredField = type.getDeclaredField(extractFieldName(method.getName()));
                            if (declaredField.getType().isAnnotationPresent(DtoModel.class)) {
                                    method.invoke(returnValue, map(getterOptional.get().invoke(object), method.getParameterTypes()[0]));
                            } else {
                                if (Collection.class.isAssignableFrom(declaredField.getType())
                                        && Class.forName(((ParameterizedType) method.getGenericParameterTypes()[0]).getActualTypeArguments()[0].getTypeName()).isAnnotationPresent(DtoModel.class)) {
                                    method.invoke(returnValue, map(getterOptional.get().invoke(object), Class.forName(((ParameterizedType) method.getGenericParameterTypes()[0]).getActualTypeArguments()[0].getTypeName())));
                                } else if (method.getParameterTypes()[0].equals(getterOptional.get().getReturnType())) {
                                    method.invoke(returnValue, getterOptional.get().invoke(object));
                                }
                            }
                        } catch (IllegalAccessException | InvocationTargetException | NoSuchFieldException | ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
                return returnValue;
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
            return modelMapper.map(object, type);
        }
    }

    private String extractFieldName(final String accessMethodName) {
        return accessMethodName.substring(3, 4).toLowerCase().concat(accessMethodName.substring(4));
    }
}
