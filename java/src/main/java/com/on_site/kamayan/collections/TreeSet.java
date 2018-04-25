package com.on_site.kamayan.collections;

import com.on_site.kamayan.Kamayan;

import java.util.function.Consumer;

public class TreeSet<T extends Comparable<T>> {
    private int size = 0;
    private Node root;

    private class Node {
        final T value;
        Node left;
        Node right;

        public Node(T value) {
            this.value = value;
        }
    }

    public int size() {
        return size;
    }

    public TreeSet<T> add(T object) {
        if (this.contains(object)) {
            return this;
        }


        size++;
        return this;
    }
    public TreeSet<T> remove(T object) {
        throw Kamayan.todo(
        );
    }

    public boolean contains(T object) {
        if (root == null) {
            return false;
        }

        Node current = root;
        while (current != null) {
            if (current.value.compareTo(object) == 0) {
                return true;
            } else if (current.value.compareTo(object) > 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return false;
    }

    public TreeSet<T> each(Consumer<T> block) {
        throw Kamayan.todo(
        );
    }
}
