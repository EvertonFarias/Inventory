package com.example.inventory.entities;

class LinkedList {
    Node head;

    void append(int data) {
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

    void display() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    void insertionSort() {
        // Se a lista estiver vazia ou contiver apenas um elemento, não é necessário ordenação
        if (head == null || head.next == null) {
            return;
        }

        Node sorted = null;
        Node current = head;

        while (current != null) {
            Node nextNode = current.next;

            // Insere o nó atual na lista ordenada
            sorted = sortedInsert(sorted, current);

            // Atualiza a cabeça da lista ordenada, se necessário
            if (sorted == current) {
                head = current;
            }

            current = nextNode;
        }
    }

    Node sortedInsert(Node sorted, Node newNode) {
        // Se a lista ordenada estiver vazia ou o novo nó for menor do que o primeiro nó da lista ordenada
        if (sorted == null || sorted.data >= newNode.data) {
            newNode.next = sorted;
            return newNode;
        } else {
            Node current = sorted;
            while (current.next != null && current.next.data < newNode.data) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
            return sorted;
        }
    }
}