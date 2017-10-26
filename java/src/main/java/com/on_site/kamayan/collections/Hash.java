package com.on_site.kamayan.collections;

import com.on_site.kamayan.Kamayan;
import com.on_site.kamayan.Ref;

import java.util.ArrayList;

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

    private void resize() {
        ArrayList<Entry> temp = new ArrayList<>();

        for (int i = 0; i < hash.length; i++) {
            if (hash[i] == null) continue;
            if (hash[i].size() == 0) continue;
            hash[i].each((entry)-> {
                    Entry current = (Entry)entry;
                    temp.add(current);
                });
        }

        hash = new DoublyLinkedList[hash.length * 2];
        this.size = 0;
        for (int i = 0; i < temp.size(); i++) {
            Entry e = temp.get(i);
            this.put(e.getKey(), e.getValue());
        }
    }

    public Hash put(Object key, Object value) {

        if ((int)(size * 0.75) > hash.length)
            resize();

        if (key == null)
            throw new NullPointerException();

        int h = key.hashCode() % hash.length;
        if (h < 0) h *= -1;
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

        int h = key.hashCode() % hash.length;
        if (h < 0) h *= -1;

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

    public boolean contains(Object key) {
        try {
            return get(key) != null;
        } catch (MissingKeyException e) {
            return false;
        }
    }
}
