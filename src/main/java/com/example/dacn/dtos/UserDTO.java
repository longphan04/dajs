package com.example.dacn.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserDTO {
    private String username;
    private List<CategoryDTO> categoryList;

    public void setCategoryList(List<CategoryDTO> categoryList) {
        this.categoryList = categoryList;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
