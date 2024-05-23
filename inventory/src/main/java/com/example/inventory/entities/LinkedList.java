package com.example.inventory.entities;

import com.example.inventory.models.ProductModel;
import java.util.Iterator;

public class LinkedList implements Iterable<ProductModel> {
    private Node head;

    public LinkedList() {
        this.head = null;
    }

    public void insert(ProductModel data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    public void display() {
        Node current = head;
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }

    @Override
    public Iterator<ProductModel> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<ProductModel> {
        private Node current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public ProductModel next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            ProductModel data = current.data;
            current = current.next;
            return data;
        }
    }

    static class Node {
        ProductModel data;
        Node next;

        Node(ProductModel data) {
            this.data = data;
            this.next = null;
        }
    }
}
