package com.example.inventory.entities;

import com.example.inventory.models.ProductModel;

class TreeNode {
    ProductModel product;
    TreeNode left;
    TreeNode right;

    public TreeNode(ProductModel product) {
        this.product = product;
        this.left = null;
        this.right = null;
    }
}
