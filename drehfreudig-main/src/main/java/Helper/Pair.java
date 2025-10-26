package Helper;

public record Pair<A, B>(A a, B b) {

    @Override
    public String toString() {
        return "(" + a + ',' + b + ')';
    }
}
