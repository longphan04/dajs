package com.example.dacn.services;

import com.example.dacn.dtos.CategoryDTO;
import com.example.dacn.dtos.HomeDTO;
import com.example.dacn.entities.Category;
import com.example.dacn.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.Tuple;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<Category> getDefaultCategories() {
        List<Category> categories = categoryRepository.findAll();
//        return categories.stream().filter(category -> category.getId() <= 8).collect(Collectors.toList());
        return categories;
    }

    public List<CategoryDTO> convertToCategoryDTOs(List<Category> categories) {
        return categories.stream().map(this::convertToCategoryDTO).collect(Collectors.toList());
    }

    public CategoryDTO convertToCategoryDTO(Category category) {
        return CategoryDTO.builder()
                .categoryId(category.getId())
                .categoryName(category.getName())
                .categoryType(category.getType())
                .build();
    }

    public List<CategoryDTO> getAvailableUserCategories(String username) {
        List<Category> categories = categoryRepository.findAllByUser(username);
        return convertToCategoryDTOs(categories);
    }

    public List<HomeDTO.TopDepositDTO> getTopDepositsOfThisMonth(String username, Double totalAmount) {
        List<Tuple> tuples = categoryRepository.getTopDepositsOfThisMonth(username);
        return tuples.stream().map(tuple -> HomeDTO.TopDepositDTO.builder()
                        .amount(tuple.get("amount", Double.class))
                        .categoryName(tuple.get("categoryName", String.class))
                        .percent(calculateAmountPercent(tuple.get("amount", Double.class), totalAmount))
                        .build())
                .collect(Collectors.toList());
    }

    public List<HomeDTO.TopWithdrawDTO> getTopWithdrawsOfThisMonth(String username, Double totalAmount) {
        List<Tuple> tuples = categoryRepository.getTopWithdrawsOfThisMonth(username);
        return tuples.stream().map(tuple -> HomeDTO.TopWithdrawDTO.builder()
                        .amount(tuple.get("amount", Double.class))
                        .categoryName(tuple.get("categoryName", String.class))
                        .percent(calculateAmountPercent(tuple.get("amount", Double.class), totalAmount))
                        .build())
                .collect(Collectors.toList());
    }

    private double calculateAmountPercent(Double amount, Double total) {
        if (total == 0) {
            return 0;
        }

        return amount / total * 100;
    }
}
