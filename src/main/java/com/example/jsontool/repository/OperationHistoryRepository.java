package com.example.jsontool.repository;


import com.example.jsontool.model.OperationHistory;
import com.example.jsontool.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OperationHistoryRepository extends JpaRepository<OperationHistory, Long> {
    List<OperationHistory> findByUser(User user);
}


