package com.example.dacn.dtos;


import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class WithdrawDTO {
    private Date date;
    private Double amount;
    private String description;
    private Long categoryId;
    private CategoryDTO categoryDTO;
}
