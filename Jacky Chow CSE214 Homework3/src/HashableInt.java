public class HashableInt implements Hashable {

    private int i;

    public HashableInt(int i) {
        this.i = i;
    }

    @Override
    public int key() {
        return i;
    }

    @Override
    public String toString() {
        return "" + i;
    }
}

