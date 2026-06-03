package main.java.co.edu.unisabana.eventSourcing;

import java.math.BigDecimal;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EventStore eventStore = new EventStore();

        // Crear cuenta
        BankAccount account = BankAccount.create("ACC-1", new BigDecimal("100"));

        account.deposit(new BigDecimal("50"));
        account.withdraw(new BigDecimal("30"));
        account.withdraw(new BigDecimal("40"));
        account.deposit(new BigDecimal("35"));

        // Guardar eventos
        eventStore.save(account.getId(), account.getUncommittedChanges());
        account.markChangesCommitted();

        // --- reconstruir desde eventos ---
        BankAccount rehydrated = new BankAccount();
        for (Event e : eventStore.load("ACC-1")) {
            rehydrated.apply(e);
        }

        System.out.println("Balance reconstruido: " + rehydrated.getBalance());

        List<Event> history = eventStore.load("ACC-1");

        for (Event event : history) {
            System.out.println(event);
        }
    }

}
