package com.autumnutil.ditio.service.dtomapperservice;

import com.autumnutil.ditio.service.DtoMapperService;
import com.autumnutil.ditio.service.dtomapperservice.dto.EntityDto;
import com.autumnutil.ditio.service.dtomapperservice.model.Entity;
import com.autumnutil.ditio.service.dtomapperservice.model.Field;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.util.Collections;


public class DtoMapperServiceTest {

    private DtoMapperService dtoMapperService = new DtoMapperService();

    @Test
    public void map() throws JsonProcessingException {
        Field field = new Field().setId("fieldId").setName("fieldName").setProtectedInfo("secret");
        Entity entity = new Entity().setId("entityId").setName("entityName").setField(field).setProtectedInfo("secret").setFields(Collections.singletonList(field));
        Object entityDto = dtoMapperService.map(entity, EntityDto.class);
        System.out.println((new ObjectMapper()).writeValueAsString(entityDto));
    }
}
