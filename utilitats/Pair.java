package utilitats;

public class Pair<P, S> {

    private final P primer;
    private final S segon;

    public static <P, S> Pair<P, S> createPair(P primer, S segon) {
        return new Pair<P, S>(primer, segon);
    }

    public Pair(P primer, S segon) {
        this.primer = primer;
        this.segon = segon;
    }

    public P first() {
        return primer;
    }

    public S second() {
        return segon;
    }

}
