package com.example.dacn.dtos;

import lombok.Data;

@Data
public class StatisticalDTO {
    private Double depositAmount;
    private Double withdrawAmount;
    private Double sumAmount;
    private int year;
    private int month;
    private String dateString;
    private boolean monthly;
}
