package com.example.dacn.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class FinanceDTO {
    private List<CategoryDTO> categoryDTOList;
}
