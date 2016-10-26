package eu.quizit.common;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class EventStream<T> {

    private List<Consumer<T>> events = new ArrayList<>();

    public void subscribe(Consumer<T> event) {
        events.add(event);
    }

    public void unsubscribe(Consumer<T> event) {
        events.remove(event);
    }

    public void publish(T t) {
        events.forEach(event -> {
            event.accept(t);
        });
    }

    public void publish() {
        events.forEach(event -> {
            event.accept(null);
        });
    }
}
