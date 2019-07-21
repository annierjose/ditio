package com.autumnutil.ditio.service.dtomapperservice.dto;

import com.autumnutil.ditio.dto.DtoModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@DtoModel
public class FieldDto {
    private String id;
    private String name;
}
