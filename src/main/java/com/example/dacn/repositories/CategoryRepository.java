package com.example.dacn.repositories;

import com.example.dacn.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Tuple;
import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(value = "select c.* from categories c inner join users_categories uc on c.id = uc.category_id inner join users u on u.id = uc.user_id where u.username = ?1", nativeQuery = true)
    List<Category> findAllByUser(@Param("username") String username);

    @Query(value = "select sum(amount) as amount, category_id, categories.name as categoryName\n" +
            "from users\n" +
            "inner join deposits\n" +
            "on users.id = deposits.user_id\n" +
            "inner join categories\n" +
            "on deposits.category_id = categories.id\n" +
            "where users.username = ?1\n" +
            "and year(deposits.date) = year(now())\n" +
            "and month(deposits.date) = month(now())" +
            "group by category_id\n" +
            "order by sum(amount) desc\n" +
            "limit 2", nativeQuery = true)
    List<Tuple> getTopDepositsOfThisMonth(@Param("username") String username);

    @Query(value = "select sum(amount) as amount, category_id, categories.name as categoryName\n" +
            "from users\n" +
            "inner join withdraws\n" +
            "on users.id = withdraws.user_id\n" +
            "inner join categories\n" +
            "on withdraws.category_id = categories.id\n" +
            "where users.username = ?1\n" +
            "and year(withdraws.date) = year(now())\n" +
            "and month(withdraws.date) = month(now())" +
            "group by category_id\n" +
            "order by sum(amount) desc\n" +
            "limit 2", nativeQuery = true)
    List<Tuple> getTopWithdrawsOfThisMonth(@Param("username") String username);
}
