package org.lnu.smartphoneservice.dto.common;

import lombok.Data;

@Data
public class ValueDto<TValue> {
    private final TValue value;
}
