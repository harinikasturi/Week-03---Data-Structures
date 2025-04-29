class MyHashMap<K, V> {
    private static final int SIZE = 16;
    private Entry<K, V>[] table;

    public MyHashMap() {
        table = new Entry[SIZE];
    }

    public void put(K key, V value) {
        int hash = key.hashCode() % SIZE;
        Entry<K, V> e = table[hash];
        if (e == null) {
            table[hash] = new Entry<>(key, value);
        } else {
            while (e.next != null) {
                if (e.key.equals(key)) {
                    e.value = value;
                    return;
                }
                e = e.next;
            }
            if (e.key.equals(key)) {
                e.value = value;
            } else {
                e.next = new Entry<>(key, value);
            }
        }
    }

    public V get(K key) {
        int hash = key.hashCode() % SIZE;
        Entry<K, V> e = table[hash];
        while (e != null) {
            if (e.key.equals(key)) {
                return e.value;
            }
            e = e.next;
        }
        return null;
    }

    public void remove(K key) {
        int hash = key.hashCode() % SIZE;
        Entry<K, V> e = table[hash];
        Entry<K, V> prev = null;
        while (e != null) {
            if (e.key.equals(key)) {
                if (prev == null) {
                    table[hash] = e.next;
                } else {
                    prev.next = e.next;
                }
                return;
            }
            prev = e;
            e = e.next;
        }
    }

    private static class Entry<K, V> {
        K key;
        V value;
        Entry<K, V> next;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}