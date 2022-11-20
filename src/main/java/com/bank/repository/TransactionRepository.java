package com.bank.repository;

import com.bank.dto.TransactionDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class TransactionRepository {

    public static List<TransactionDTO> transactionDTOList = new ArrayList<>();

    public TransactionDTO save(TransactionDTO transactionDTO){
         transactionDTOList.add(transactionDTO);
         return transactionDTO;
    }

    public List<TransactionDTO> findAll() {
        return transactionDTOList;
    }

    public List<TransactionDTO> lastTransaction() {
        return transactionDTOList.stream().sorted(Comparator.comparing(TransactionDTO::getCreationDate).reversed())
                .limit(10).collect(Collectors.toList());
    }
    public List<TransactionDTO> getTransactionListById(Long id){
        return transactionDTOList.stream().filter(transactionDTO -> transactionDTO.getSender().equals(id)
            || transactionDTO.getReceiver().equals(id)).collect(Collectors.toList());
    }
}
