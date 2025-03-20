package com.tujuhsembilan.user_service.dto.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaginationResponseDto {
    private Object data;
    private Integer page;
    private Integer size;
    private Integer totalPage;
    private Long totalData;
}
