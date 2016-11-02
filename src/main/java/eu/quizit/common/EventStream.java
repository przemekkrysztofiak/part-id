package eu.quizit.common;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class EventStream<E> {

    private List<Consumer<E>> events = new ArrayList<>();

    public void subscribe(Consumer<E> eventConsumer) {
        events.add(eventConsumer);
    }

    public void publish(E event) {
        events.forEach(eventConsumer -> {
            eventConsumer.accept(event);
        });
    }

    public void publish() {
        events.forEach(eventConsumer -> {
            eventConsumer.accept(null);
        });
    }
}
