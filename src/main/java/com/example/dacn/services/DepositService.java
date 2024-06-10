package com.example.dacn.services;

import com.example.dacn.dtos.CategoryDTO;
import com.example.dacn.dtos.DepositDTO;
import com.example.dacn.entities.Category;
import com.example.dacn.entities.Deposit;
import com.example.dacn.entities.User;
import com.example.dacn.repositories.DepositRepository;
import com.example.dacn.utils.DateTimeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.Tuple;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepositService {
    private final DepositRepository depositRepository;
    private final UserService userService;
    private final CategoryService categoryService;

    public DepositDTO save(DepositDTO depositDTO) {
        Deposit deposit = new Deposit();
        deposit.setDate(DateTimeUtil.convertDateToTimeStamp(depositDTO.getDate()));
        deposit.setAmount(depositDTO.getAmount());
        deposit.setDescription(depositDTO.getDescription());
        deposit.setUser(User.builder().id(userService.getLoginUserId()).build());
        deposit.setCategory(Category.builder().id(depositDTO.getCategoryId()).build());
        depositRepository.save(deposit);
        return depositDTO;
    }

    public List<DepositDTO> getAllDepositsOfCurrentUser() {
        String username = userService.getLoginUsername();
        List<Deposit> deposits = depositRepository.findAllByUsername(username);
        return this.convertToDepositDTO(deposits);
    }

    public Double getSumAllDepositOfCurrentUser() {
        String username = userService.getLoginUsername();
        return depositRepository.getSumAllDepositOfCurrentUser(username);
    }

    public Double getCurrentMonthDepositAmount() {
        String username = userService.getLoginUsername();
        return depositRepository.getCurrentMonthDepositAmount(username);
    }

    public List<DepositDTO> getRecentTransactions() {
        String username = userService.getLoginUsername();
        List<Tuple> tuples = depositRepository.getRecentTransactions(username);
        return tuples.stream().map(tuple -> DepositDTO.builder()
                .amount(tuple.get("amount", Double.class))
                .date(tuple.get("date", Timestamp.class))
                .description(tuple.get("description", String.class))
                .categoryDTO(CategoryDTO.builder()
                        .categoryName(tuple.get("categoryName", String.class))
                        .categoryId(tuple.get("categoryId", BigInteger.class).longValue())
                        .build())
                .build()).collect(Collectors.toList());
    }

    public List<DepositDTO> getTransactionsOnDay(String username, Date date) {
        List<Tuple> tuples = depositRepository.getTransactionsOnDay(username, date);
        return tuples.stream().map(tuple -> DepositDTO.builder()
                .amount(tuple.get("amount", Double.class))
                .date(tuple.get("date", Timestamp.class))
                .description(tuple.get("description", String.class))
                .categoryDTO(CategoryDTO.builder()
                        .categoryName(tuple.get("categoryName", String.class))
                        .categoryId(tuple.get("categoryId", BigInteger.class).longValue())
                        .build())
                .build()).collect(Collectors.toList());
    }

    private List<DepositDTO> convertToDepositDTO(List<Deposit> deposits) {
        return deposits.stream().map(deposit -> DepositDTO.builder()
                .amount(deposit.getAmount())
                .date(DateTimeUtil.convertTimestampToDate(deposit.getDate()))
                .description(deposit.getDescription())
                .categoryDTO(categoryService.convertToCategoryDTO(deposit.getCategory()))
                .build()).collect(Collectors.toList());
    }

}
