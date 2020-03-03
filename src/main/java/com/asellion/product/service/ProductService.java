package com.asellion.product.service;

import com.asellion.product.dto.ProductDto;

import java.util.List;


/**
 * Product service to do manage  it.
 *
 * @author Zahra Akbari (akbari.zahra@gmail.com)
 */
public interface ProductService {

    /**
     * Creates a new product.
     *
     * @return an instance of ProductDto
     */
    ProductDto createProduct(ProductDto productDto);

    /**
     * Get all saved product.
     *
     * @return a list of ProductDto
     */
    List<ProductDto> getAllProducts();

    /**
     * Find a product
     *
     * @param id identifier of a product
     * @return a ProductDto by id
     */
    ProductDto findProductById(Integer id);

    /**
     * Update a product
     *
     * @param productDto
     * @return an updated ProductDto
     */
    ProductDto updateProduct(ProductDto productDto, Integer id);
}
