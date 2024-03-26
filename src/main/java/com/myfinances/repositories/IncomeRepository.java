package com.myfinances.repositories;

import com.myfinances.model.entities.Income;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IncomeRepository extends JpaRepository<Income,Long> {
    @Transactional
    @Modifying
    @Query("DELETE FROM Income i WHERE i.user.id = :userId")
    void deleteByUser(Long userId);
}
