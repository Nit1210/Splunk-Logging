package com.Activepharmavskp.billing.repository;
import com.Activepharmavskp.billing.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
