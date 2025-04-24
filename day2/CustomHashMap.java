class CustomHashMap {

    static class Entry {
        int key;
        int value;
        Entry next;

        Entry(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int SIZE = 1000;
    private Entry[] table;

    CustomHashMap() {
        table = new Entry[SIZE];
    }

    private int hash(int key) {
        return key % SIZE;
    }

    void put(int key, int value) {
        int index = hash(key);
        Entry head = table[index];

        Entry current = head;
        while (current != null) {
            if (current.key == key) {
                current.value = value;
                return;
            }
            current = current.next;
        }

        Entry newEntry = new Entry(key, value);
        newEntry.next = head;
        table[index] = newEntry;
    }

    Integer get(int key) {
        int index = hash(key);
        Entry current = table[index];

        while (current != null) {
            if (current.key == key) {
                return current.value;
            }
            current = current.next;
        }

        return null;
    }

    void remove(int key) {
        int index = hash(key);
        Entry current = table[index];
        Entry prev = null;

        while (current != null) {
            if (current.key == key) {
                if (prev == null) {
                    table[index] = current.next;
                } else {
                    prev.next = current.next;
                }
                return;
            }
            prev = current;
            current = current.next;
        }
    }

    public static void main(String[] args) {
        CustomHashMap map = new CustomHashMap();
        map.put(1, 100);
        map.put(2, 200);
        map.put(102, 300);

        System.out.println("Value for key 1: " + map.get(1));
        System.out.println("Value for key 102: " + map.get(102));

        map.remove(1);
        System.out.println("Value for key 1 after deletion: " + map.get(1));
    }
}
