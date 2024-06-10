package com.example.dacn.services;

import com.example.dacn.dtos.CategoryDTO;
import com.example.dacn.dtos.DepositDTO;
import com.example.dacn.dtos.FinanceDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FinanceService {
    private final UserService userService;
    private final CategoryService categoryService;

    public FinanceDTO initDepositDTO() {
        String username = userService.getLoginUsername();
        List<CategoryDTO> categoryDTOList = categoryService.getAvailableUserCategories(username);
        return FinanceDTO.builder().categoryDTOList(categoryDTOList).build();
    }
}
