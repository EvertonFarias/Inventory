package com.example.inventory.entities;

public class Fila {
    private int capacidade;
    private int tamanho;
    private int[] elementos;
    private int inicio;
    private int fim;

    public Fila(int capacidade) {
        this.capacidade = capacidade;
        this.tamanho = 0;
        this.elementos = new int[capacidade];
        this.inicio = 0;
        this.fim = -1;
    }

    public boolean estaVazia() {
        return tamanho == 0;
    }

    public boolean estaCheia() {
        return tamanho == capacidade;
    }

    public void enfileirar(int elemento) {
        if (!estaCheia()) {
            fim = (fim + 1) % capacidade;
            elementos[fim] = elemento;
            tamanho++;
        } else {
            System.out.println("A fila está cheia.");
        }
    }

    public int desenfileirar() {
        if (!estaVazia()) {
            int elementoRemovido = elementos[inicio];
            inicio = (inicio + 1) % capacidade;
            tamanho--;
            return elementoRemovido;
        } else {
            System.out.println("A fila está vazia.");
            return -1;
        }
    }

    public int primeiro() {
        if (!estaVazia()) {
            return elementos[inicio];
        } else {
            System.out.println("A fila está vazia.");
            return -1;
        }
    }

    public int tamanho() {
        return tamanho;
    }
}
