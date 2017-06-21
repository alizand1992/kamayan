package com.on_site.kamayan.collections;

import com.on_site.kamayan.Kamayan;

public class Stack {
    private Integer maxSize;
    private DoublyLinkedList list;

    public Stack() {
        this(null);
    }

    public Stack(Integer maxSize) {
        this.maxSize = maxSize;
        // You may use any of the collections you've built so far, though you
        // will need to implement `each` for that collection if you use
        // something other than DoublyLinkedList.
        this.list = new DoublyLinkedList();
    }

    public int size() {
        return list.size();
    }

    public Stack push(Object value) {
        // throw Kamayan.todo(
        //     "The push(Object) method should add the argument to the end of the",
        //     "stack, which should increase the size by 1. The return value must",
        //     "be this. If the maxSize was specified when constructing the Stack",
        //     "(that is, it is not null), then a StackOverflowException should be",
        //     "raised before adding the value if the stack is already at the",
        //     "capacity."
        // );

        if (maxSize != null && size() == maxSize) {
            throw new StackOverflowException("Maximum size has been reached");
        }

        list.add(value);
        return this;
    }

    public Object pop() {
        // throw Kamayan.todo(
        //     "The pop() method should remove and return the last value in the",
        //     "stack. An IndexOutOfBoundsException should be raised if the Stack",
        //     "is empty."
        // );

        if (size() == 0) {
            throw new IndexOutOfBoundsException("The stack is empty");
        }
        Object value = list.last();
        list.deleteLast();
        return value;
    }

    public boolean isEmpty() {
        if (size() == 0)
            return true;
        return false;
    }

    public Object peek() {
        // throw Kamayan.todo(
        //     "The peek() method should return the last value in the stack,",
        //     "without removing any elements in the stack."
        // );
        if (isEmpty())
            throw new IndexOutOfBoundsException("The stack is empty.");
        return list.last();
    }
}
