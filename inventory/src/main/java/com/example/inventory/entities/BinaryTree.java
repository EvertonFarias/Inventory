package com.example.inventory.entities;

import com.example.inventory.models.ProductModel;

import java.util.ArrayList;
import java.util.List;

public class BinaryTree {
    private TreeNode root;

    public BinaryTree() {
        this.root = null;
    }

    public void insert(ProductModel product) {
        this.root = insertRec(this.root, product);
    }

    private TreeNode insertRec(TreeNode root, ProductModel product) {
        if (root == null) {
            root = new TreeNode(product);
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

    private void traverseInOrderRec(TreeNode root, List<ProductModel> produtos) {
        if (root != null) {
            traverseInOrderRec(root.left, produtos);
            produtos.add(root.product);
            traverseInOrderRec(root.right, produtos);
        }
    }
}

