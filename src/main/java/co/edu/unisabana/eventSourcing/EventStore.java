package main.java.co.edu.unisabana.eventSourcing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventStore {
    private final Map<String, List<Event>> store = new HashMap<>();

    public void save(String aggregateId, List<Event> events) {
        store.computeIfAbsent(aggregateId, k -> new ArrayList<>())
                .addAll(events);
    }

    public List<Event> load(String aggregateId) {
        return store.getOrDefault(aggregateId, new ArrayList<>());
    }
}
