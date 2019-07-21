package com.autumnutil.ditio.service.dtomapperservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class Entity {
    private String id;
    private String name;
    private String protectedInfo;
    private Field field;
    private List<Field> fields;
}
