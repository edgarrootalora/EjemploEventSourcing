package main.java.co.edu.unisabana.eventSourcing;

import java.math.BigDecimal;

public class MoneyDeposited implements Event{

    private final String accountId;
    private final BigDecimal amount;

    public MoneyDeposited(String accountId, BigDecimal amount) {
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
        return "MoneyDeposited{" +
                "accountId='" + accountId + '\'' +
                ", amount=" + amount +
                '}';
    }
}
