package com.asellion.product.repository;

import com.asellion.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for Product.
 *
 * @author Zahra Akbari (akbari.zahra@gmail.com)
 */

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
