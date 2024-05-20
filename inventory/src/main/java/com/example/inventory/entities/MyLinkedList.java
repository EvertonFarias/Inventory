package com.example.inventory.entities;

import com.example.inventory.models.ProductModel;

public class MyLinkedList {
    public Node head;

    public void append(ProductModel data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            return;
        }
        Node last = head;
        while (last.next != null) {
            last = last.next;
        }
        last.next = newNode;
    }

    public MyLinkedList display() {
        Node current = head;
        while (current != null) {
            System.out.print(current.getData() + " ");
            current = current.next;
        }
        System.out.println();
        return this;
    }
    static class Node {
        private final ProductModel data;
        Node next;

        // Construtor
        Node(ProductModel data) {
            this.data = data;
            this.next = null;
        }
        public ProductModel getData() {
            return data;
        }
    }



}