package com.bank.repository;

import com.bank.dto.TransactionDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.transaction.Transaction;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    @Query(value = "SELECT * FROM transactions t order by creation_date DESC limit 10",nativeQuery = true)
    List<Transaction> last10Transaction();

}
