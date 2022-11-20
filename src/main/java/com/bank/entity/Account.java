package com.bank.entity;

import com.bank.enums.AccountStatus;
import com.bank.enums.AccountType;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Table(name = "accounts")
@Entity
public class Account extends BaseEntity{

    private Long userId;
    private BigDecimal balance;
    @Column(columnDefinition = "DATE")
    private LocalDateTime creationDate;
    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;
    @Enumerated(EnumType.STRING)
    private AccountType accountType;


}
