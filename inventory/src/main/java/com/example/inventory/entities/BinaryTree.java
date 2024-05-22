package com.example.inventory.entities;

import com.example.inventory.models.ProductModel;

import java.util.ArrayList;
import java.util.List;

public class BinaryTree {
    private TreeNodeBinary root;

    public BinaryTree() {
        this.root = null;
    }

    public void insert(ProductModel product) {
        this.root = insertRec(this.root, product);
    }

    private TreeNodeBinary insertRec(TreeNodeBinary root, ProductModel product) {
        if (root == null) {
            root = new TreeNodeBinary(product);
            return root;
        }

        // Comparando com base em algum critério, por exemplo, o ID do produto
        if (product.getIdProduct() < root.product.getIdProduct()) {
            root.left = insertRec(root.left, product);
        } else if (product.getIdProduct() > root.product.getIdProduct()) {
            root.right = insertRec(root.right, product);
        }

        return root;
    }

    public List<ProductModel> traverseInOrder() {
        List<ProductModel> produtos = new ArrayList<>();
        traverseInOrderRec(root, produtos);
        return produtos;
    }

    private void traverseInOrderRec(TreeNodeBinary root, List<ProductModel> produtos) {
        if (root != null) {
            traverseInOrderRec(root.right, produtos); // Visita o nó à direita primeiro
            produtos.add(root.product);
            traverseInOrderRec(root.left, produtos); // Visita o nó à esquerda por último
        }
    }


}

