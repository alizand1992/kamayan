package com.on_site.kamayan.collections;

import com.on_site.kamayan.Kamayan;
import com.on_site.kamayan.Ref;

public class Hash {
    private DoublyLinkedList[] hash;
    private int size;

    private static class Entry {
        private final Object key;
        private final Object value;

        public Entry(Object key, Object value) {
            this.key = key;
            this.value = value;
        }

        public Object getKey() {
            return key;
        }

        public Object getValue() {
            return value;
        }
    }

    public Hash() {
        this.hash = new DoublyLinkedList[10];
        this.size = 0;
    }

    public int size() {
        return size;
    }

    public Hash put(Object key, Object value) {

        if (key == null)
            throw new NullPointerException();

        int h = key.hashCode() % 10;
        if (hash[h] == null)
            hash[h] = new DoublyLinkedList();

        if (get(key) == null) {
            hash[h].add(new Entry(key, value));
            this.size++;
        } else {
            DoublyLinkedList temp = new DoublyLinkedList();
            hash[h].each((entry) -> {
                    Entry current = (Entry)entry;
                    if (current.getKey().equals(key)) {
                        temp.add(new Entry(key, value));
                    } else {
                        temp.add(current);
                    }
                });
            hash[h] = temp;
        }

        return this;
    }

    public Object get(Object key) {

        int h = key.hashCode() % 10;

        if (hash[h] == null)
            throw new MissingKeyException();

        DoublyLinkedList temp = hash[h];

        Ref<Object> ref = new Ref<>();
        temp.each((entry)-> {
                Entry current = (Entry)entry;
                if (current.getKey().equals(key))
                    ref.set(current.getValue());
            });

        return ref.get();
    }

    private void resize() {

    }

    public boolean contains(Object key) {
        try {
            return get(key) != null;
        } catch (MissingKeyException e) {
            return false;
        }
    }
}
