import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * A simple implementation of {@link java.util.function} interfaces
 * to support android versions before api 24.
 * <p>
 * author: github/fCat97
 */
public class MyStream<T> {
    private final Collection<T> items;

    private MyStream(Collection<T> items) {
        this.items = items;
    }

    /**
     * Create a stream object
     * @param items items of stream
     * @return Stream object
     */
    public static <T> MyStream<T> of(List<T> items) {
        return new MyStream<>(items);
    }

    public static <T> MyStream<T> of(Collection<T> items) {
        return new MyStream<>(items);
    }

    /**
     * Convert the stream to list
     * @return list of items
     */
    public List<T> toList() {
        return new ArrayList<>(items);
    }

    public MyStream<T> filter(Predicate<T> predicate) {
        List<T> result = new ArrayList<>();
        for (T t: items) {
            if (predicate.test(t)) {
                result.add(t);
            }
        }

        return new MyStream<>(result);
    }

    public <R> MyStream<R> map(Function<T, R> function) {
        List<R> result = new ArrayList<>();
        for (T t: items) {
            R r = function.apply(t);
            result.add(r);
        }

        return new MyStream<>(result);
    }

    public void forEach(Consumer<T> consumer) {
        for (T t: items) {consumer.consume(t);}
    }

    public interface Predicate<T> { boolean test(T t); }
    public interface Function<T, R> { R apply(T t); }

    public interface Consumer<T> { void consume(T t); }
}
