import java.util.ArrayList;
import java.util.function.Function;

public class DoubleHashTable<V extends Hashable> implements OpenAddressTable<V> {
    private int size;
    private Function<Integer, Integer> h1, h2;
    private ArrayList<V> table;
    private int elemnum;

    public DoubleHashTable(int size, Function<Integer, Integer> h1, Function<Integer, Integer> h2) {
        this.size = size;
        this.h1 = h1;
        this.h2 = h2;
        this.table = new ArrayList<>(this.size);

        // null the arraylist;
        for (int i = 0; i < size; i++) {
            table.add(null);
        }
        this.elemnum = 0;
    }

    @Override
    public boolean isEmpty() {
        return elemnum == 0;
    }

    @Override
    public void insert(V value) {
        int key = Math.abs(value.key());
        int probNum = 0;

        if (find(value) != -1) throw new ArrayIndexOutOfBoundsException();

        while (probNum < size) {
            int index = hash(key, probNum++);
            if (table.get(index) == null) {
                table.set(index, value);
                elemnum++;
                return;
            }
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public V delete(V value) {
        int index = find(value);
        if (index != -1) {
            V val = table.get(index);
            table.set(index, null);
            elemnum--;
            return val;
        }
        throw new NullPointerException();
    }

    @Override
    public int find(V value) {
        {
            int key = Math.abs(value.key());
            int probNum = 0;

            while (probNum < size) {
                int index = hash(key, probNum++);
                if (table.get(index) == null) {
                    continue;
                } else if (table.get(index).key() == key) {
                    return index;
                }
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < size; i++) {
            V value = table.get(i);
            if (value == null) {
                result.append(i).append(" -> [null] ");
                continue;
            }
            result.append(i).append(" -> [").append(value.key()).append(", ").append(value).append("] ");
        }
        return result.toString();
    }

    @Override
    public int hash(int key, int probenumber) {
        int index = h1.apply(key);
        int jump = h2.apply(key);
        int newIndex = index + jump * probenumber;
        return newIndex % size;
    }

    public static void main(String[] args) {
        // the hash function and the hashableInt type is made for the last question of recitation 10.
        Function<Integer, Integer> h1 = a -> a % 7;
        Function<Integer, Integer> h2 = a -> 5 - a % 5;

        DoubleHashTable<HashableInt> table1 = new DoubleHashTable<>(7, h1, h2);
        table1.insert(new HashableInt(19));
        table1.insert(new HashableInt(26));
        table1.insert(new HashableInt(13));
        table1.insert(new HashableInt(48));
        table1.insert(new HashableInt(17));

        System.out.println(table1.toString());
        System.out.println(table1.table.toString());
        System.out.println(table1.find(new HashableInt(19)));
        System.out.println(table1.delete(new HashableInt(48)));
        System.out.println(table1.toString());
        System.out.println(table1.isEmpty());

        //Usage of HashableString.
        DoubleHashTable<HashableString> table2 = new DoubleHashTable<>(5, h1, h2);
        table2.insert(new HashableString("apple"));
        table2.insert(new HashableString("orange"));
        table2.insert(new HashableString("banana"));
        System.out.println(table2.toString());
        System.out.println(table2.isEmpty());
        System.out.println(table2.find(new HashableString("apple")));
    }
}
