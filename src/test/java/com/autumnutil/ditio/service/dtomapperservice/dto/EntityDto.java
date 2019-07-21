package com.autumnutil.ditio.service.dtomapperservice.dto;

import com.autumnutil.ditio.dto.DtoModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@DtoModel
public class EntityDto {
    private String id;
    private String name;
    private FieldDto field;
    private List<FieldDto> fields;
}
