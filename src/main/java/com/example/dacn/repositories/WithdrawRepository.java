package com.example.dacn.repositories;

import com.example.dacn.entities.Withdraw;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WithdrawRepository extends JpaRepository<Withdraw, Integer> {
    @Query(value = "select ifnull(sum(amount), 0) from withdraws inner join users on users.id = withdraws.user_id where username = ?1 and year(date) = ?2", nativeQuery = true)
    Double getWithdrawsInYear(@Param("username") String username, @Param("year") int year);

    @Query(value = "select sum(amount) from withdraws inner join users on users.id = withdraws.user_id where username = ?1 and year(date) = ?2 and month(date) = ?3", nativeQuery = true)
    Double getWithdrawsInMonth(@Param("username") String username, @Param("year") int year, @Param("month") int month);

    @Query(value = "select sum(amount) from withdraws inner join users on users.id = withdraws.user_id where username = ?1 and year(date) = ?2 and month(date) = ?3 and day(date) = ?4", nativeQuery = true)
    Double getWithdrawsOnDay(@Param("username") String username, @Param("year") int year, @Param("month") int month, @Param("day") int day);

    @Query(value = "select ifnull(sum(amount), 0) from withdraws inner join users on users.id = withdraws.user_id where username = ?1", nativeQuery = true)
    Double getSumAllWithdrawOfCurrentUser(@Param("usename") String username);

    @Query(value = "select ifnull(sum(amount), 0) from withdraws inner join users on users.id = withdraws.user_id where username = ?1 and year(date) = year(now()) and month(date) = month(now())", nativeQuery = true)
    Double getCurrentMonthWithdrawAmount(@Param("username") String username);

}
