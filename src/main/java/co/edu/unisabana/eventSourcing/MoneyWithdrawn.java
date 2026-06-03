package main.java.co.edu.unisabana.eventSourcing;

import java.math.BigDecimal;

public class MoneyWithdrawn implements Event{

    private final String accountId;
    private final BigDecimal amount;

    public MoneyWithdrawn(String accountId, BigDecimal amount) {
        this.accountId = accountId;
        this.amount = amount;
    }

    public String getAccountId() {
        return accountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "MoneyWithdrawn{" +
                "accountId='" + accountId + '\'' +
                ", amount=" + amount +
                '}';
    }
}
