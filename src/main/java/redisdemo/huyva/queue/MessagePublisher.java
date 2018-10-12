package redisdemo.huyva.queue;

public interface MessagePublisher {
    void publish(final String message);
}
