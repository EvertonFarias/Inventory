package com.example.inventory.entities;

import com.example.inventory.models.ProductModel;

class TreeNodeBinary {
    ProductModel product;
    TreeNodeBinary left;
    TreeNodeBinary right;

    public TreeNodeBinary(ProductModel product) {
        this.product = product;
        this.left = null;
        this.right = null;
    }
}
