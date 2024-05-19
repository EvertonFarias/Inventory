package com.example.inventory.entities;

import com.example.inventory.models.ProductModel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SplayTree {
    private TreeNode root;

    public SplayTree() {
        this.root = null;
    }

    public void insert(ProductModel product) {
        if (root == null) {
            root = new TreeNode(product);
            return;
        }
        root = splay(root, product.getPrice());

        if (root.product.getPrice().compareTo(product.getPrice()) == 0) {
            root.products.add(product);
        } else {
            TreeNode newNode = new TreeNode(product);
            if (product.getPrice().compareTo(root.product.getPrice()) < 0) {
                newNode.right = root;
                newNode.left = root.left;
                root.left = null;
            } else {
                newNode.left = root;
                newNode.right = root.right;
                root.right = null;
            }
            root = newNode;
        }
    }

    private TreeNode splay(TreeNode root, BigDecimal price) {
        if (root == null || root.product.getPrice().compareTo(price) == 0) {
            return root;
        }
        System.out.println("Entrei no Splay");

        if (price.compareTo(root.product.getPrice()) < 0) {
            if (root.left == null) return root;

            if (price.compareTo(root.left.product.getPrice()) < 0) {
                root.left.left = splay(root.left.left, price);
                root = rotateRight(root);
            } else if (price.compareTo(root.left.product.getPrice()) > 0) {
                root.left.right = splay(root.left.right, price);
                if (root.left.right != null) {
                    root.left = rotateLeft(root.left);
                }
            }

            return root.left == null ? root : rotateRight(root);
        } else {
            if (root.right == null) return root;

            if (price.compareTo(root.right.product.getPrice()) < 0) {
                root.right.left = splay(root.right.left, price);
                if (root.right.left != null) {
                    root.right = rotateRight(root.right);
                }
            } else if (price.compareTo(root.right.product.getPrice()) > 0) {
                root.right.right = splay(root.right.right, price);
                root = rotateLeft(root);
            }

            return root.right == null ? root : rotateLeft(root);
        }
    }

    private TreeNode rotateRight(TreeNode root) {
        TreeNode newRoot = root.left;
        root.left = newRoot.right;
        newRoot.right = root;
        return newRoot;
    }

    private TreeNode rotateLeft(TreeNode root) {
        TreeNode newRoot = root.right;
        root.right = newRoot.left;
        newRoot.left = root;
        return newRoot;
    }

    public List<ProductModel> traverseInOrder() {
        List<ProductModel> products = new ArrayList<>();
        traverseInOrderRec(root, products);
        return products;
    }

    private void traverseInOrderRec(TreeNode root, List<ProductModel> products) {
        if (root != null) {
            traverseInOrderRec(root.left, products);
            for (ProductModel product : root.products) {
                if (!products.contains(product)) { // Verifica se o produto já está na lista
                    products.add(product);
                }
            }
            traverseInOrderRec(root.right, products);
        }
    }
    public List<ProductModel> traversePreOrder() {
        List<ProductModel> products = new ArrayList<>();
        traversePreOrderRec(root, products);
        return products;
    }

    private void traversePreOrderRec(TreeNode root, List<ProductModel> products) {
        if (root != null) {
            for (ProductModel product : root.products) {
                products.add(product);
            }
            traversePreOrderRec(root.left, products);
            traversePreOrderRec(root.right, products);
        }
    }


    public void remove(Long idProduct, ProductModel product) {
        if (root == null) {
            return; // Árvore vazia
        }

        root = splay(root, product.getPrice());

        // Encontra o produto na lista de produtos do nó
        boolean removed = root.products.removeIf(p -> p.getIdProduct().equals(idProduct));

        // Se o produto foi removido da lista, não é necessário fazer mais nada
        if (removed) return;


    }

    public void update(ProductModel product) {
        if (root == null) {
            return; // Árvore vazia, nada a fazer
        }

        remove(product.getIdProduct(), product);
        insert(product);
    }

    private static class TreeNode {
        ProductModel product;
        List<ProductModel> products;
        TreeNode left, right;

        public TreeNode(ProductModel product) {
            this.product = product;
            this.products = new ArrayList<>();
            this.products.add(product);
            this.left = this.right = null;
        }
    }


}
