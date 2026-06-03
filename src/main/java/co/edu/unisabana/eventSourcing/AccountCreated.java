package main.java.co.edu.unisabana.eventSourcing;

import java.math.BigDecimal;

public class AccountCreated implements Event{

    private final String accountId;
    private final BigDecimal initialBalance;

    public AccountCreated(String accountId, BigDecimal initialBalance) {
        this.accountId = accountId;
        this.initialBalance = initialBalance;
    }

    public String getAccountId() {
        return accountId;
    }

    public BigDecimal getInitialBalance() {
        return initialBalance;
    }

    @Override
    public String toString() {
        return "AccountCreated{" +
                "accountId='" + accountId + '\'' +
                ", initialBalance=" + initialBalance +
                '}';
    }
}
