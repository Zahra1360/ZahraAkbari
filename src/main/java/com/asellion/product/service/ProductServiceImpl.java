package com.asellion.product.service;

import com.asellion.product.exception.AsellionServiceException;
import com.asellion.product.exception.ErrorCode;
import com.asellion.product.repository.ProductRepository;
import com.asellion.product.dto.ProductDto;
import com.asellion.product.entity.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * This class implements ProductService to manage product;
 *
 * @author Zahra Akbari (akbari.zahra@gmail.com)
 */
@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    private Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        logger.info("Create a product");
        Product product = new Product();
        BeanUtils.copyProperties(productDto, product);
        product = productRepository.save(product);
        logger.info("A new product is created");
        return product.getLightDto();
    }

    @Override
    public List<ProductDto> getAllProducts() {
        logger.info("Get a list of products");
        List<ProductDto> productList = productRepository.findAll().stream().map(p -> p.getLightDto()).collect(Collectors.toList());
        return productList;
    }

    @Override
    public ProductDto findProductById(Integer id) {
        logger.info(String.format("Get one product from the list by Id: %d ", id));
        Optional<Product> productOptional = productRepository.findById(id);
        if (!productOptional.isPresent())
            throw new AsellionServiceException(ErrorCode.PRODUCT_NOT_FOUND, "Product not found");
        return productOptional.get().getLightDto();
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto, Integer id) {
        logger.info(String.format("Updating a product by Id: %d ", id ));
        return productRepository.findById(id).map(product -> {
            product.setName(productDto.getName());
            product.setCurrentPrice(productDto.getCurrentPrice());
            product.setLastUpdate(productDto.getLastUpdate());
            return productRepository.save(product).getLightDto();
        }).orElseThrow(() -> new AsellionServiceException(ErrorCode.PRODUCT_NOT_FOUND, String.format("Product not found by id: %d", productDto.getId())));
    }
}
