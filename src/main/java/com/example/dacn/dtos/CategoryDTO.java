package com.example.dacn.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryDTO {
    private Long categoryId;
    private String categoryName;
    private String categoryType;
}
