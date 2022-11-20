package com.bank.entity;

import com.bank.dto.AccountDTO;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transactions")
@Entity
public class Transaction extends BaseEntity{

    @ManyToOne
    private Account sender;
    @ManyToOne
    private Account receiver;

    private BigDecimal amount;

    private String message;
    @Column(columnDefinition = "DATE")
    private LocalDateTime creationDate;
}
