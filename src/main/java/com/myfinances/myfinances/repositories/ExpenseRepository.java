package com.myfinances.myfinances.repositories;

import com.myfinances.myfinances.entities.Expense;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    @Transactional
    @Modifying
    @Query("DELETE FROM Expense i WHERE i.user.id = :userId")
    void deleteByUser(Long userId);
}
