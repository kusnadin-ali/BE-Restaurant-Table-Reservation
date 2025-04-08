package com.tujuhsembilan.core.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto<T> {
    private String message;

    private T result;

    private String code;
}
