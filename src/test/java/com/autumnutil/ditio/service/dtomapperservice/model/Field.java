package com.autumnutil.ditio.service.dtomapperservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class Field {
    private String id;
    private String name;
    private String protectedInfo;
}
