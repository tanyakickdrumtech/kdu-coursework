package crypto.assign1;

/**
 * Represents a simple generic pair of two values (first and second).
 *
 * @param <A> Type of the first element in the pair.
 * @param <B> Type of the second element in the pair.
 */
public class Pair<A, B> {
    private A first;
    private B second;

    /**
     * Constructs a Pair with the specified first and second values.
     *
     * @param first  The first element of the pair.
     * @param second The second element of the pair.
     */
    public Pair(A first, B second) {
        this.first = first;
        this.second = second;
    }

    /**
     * Gets the first element of the pair.
     *
     * @return The first element.
     */
    public A getFirst() {
        return first;
    }

    /**
     * Gets the second element of the pair.
     *
     * @return The second element.
     */
    public B getSecond() {
        return second;
    }

    /**
     * Sets the first element of the pair.
     *
     * @param first The new value for the first element.
     */
    public void setFirst(A first) {
        this.first = first;
    }

    /**
     * Returns a string representation of the Pair.
     *
     * @return A string representation of the Pair.
     */
    @Override
    public String toString() {
        return "Pair{" +
                "first=" + first +
                ", second=" + second +
                '}';
    }
}
