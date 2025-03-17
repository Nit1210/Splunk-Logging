package com.Activepharmavskp.billing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

    @RestController
    @RequestMapping("/api/products")
    public class ProductController {

        @Autowired
        private ProductService productService;

        // Add product
        @PostMapping
        public ResponseEntity<String> addProduct(@RequestBody ProductDto productDto) {
            productService.addProduct(productDto);
            return new ResponseEntity<>("Product added successfully!", HttpStatus.CREATED);
        }

        // Edit product details
        @PutMapping("/{id}")
        public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {
            productService.updateProduct(id, productDto);
            return new ResponseEntity<>("Product updated successfully!", HttpStatus.OK);
        }

        // View product list
        @GetMapping
        public ResponseEntity<List<Product>> getProducts() {
            return ResponseEntity.ok(productService.getAllProducts());
        }
    }

    @RestController
    @RequestMapping("/api/dashboard")
    public class DashboardController {

        @Autowired
        private ProductService productService;

        @Autowired
        private BillService billService;

        // Fetch dashboard data
        @GetMapping
        public ResponseEntity<DashboardData> getDashboardData() {
            DashboardData data = productService.getDashboardData();
            return ResponseEntity.ok(data);
        }
    }


