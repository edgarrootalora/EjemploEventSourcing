package main.java.co.edu.unisabana.eventSourcing;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BankAccount {

    private String id;
    private BigDecimal balance = BigDecimal.ZERO;

    // Event log (esto normalmente vive en una DB)
    private final List<Event> changes = new ArrayList<>();

    public List<Event> getUncommittedChanges() {
        return changes;
    }

    public void markChangesCommitted() {
        changes.clear();
    }

    // --- Factory ---
    public static BankAccount create(String id, BigDecimal initialBalance) {
        BankAccount account = new BankAccount();
        account.applyChange(new AccountCreated(id, initialBalance));
        return account;
    }

    // --- Commands ---
    public void deposit(BigDecimal amount) {
        applyChange(new MoneyDeposited(id, amount));
    }

    public void withdraw(BigDecimal amount) {
        if (balance.compareTo(amount) < 0) {
            throw new IllegalArgumentException("Fondos insuficientes");
        }
        applyChange(new MoneyWithdrawn(id, amount));
    }

    // --- Event handling ---
    private void applyChange(Event event) {
        apply(event);
        changes.add(event); // se guarda para persistencia
    }

    public void apply(Event event) {
        if (event instanceof AccountCreated e) {
            this.id = e.getAccountId();
            this.balance = e.getInitialBalance();
        }

        if (event instanceof MoneyDeposited e) {
            this.balance = this.balance.add(e.getAmount());
        }

        if (event instanceof MoneyWithdrawn e) {
            this.balance = this.balance.subtract(e.getAmount());
        }
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public String getId() {
        return id;
    }
}
