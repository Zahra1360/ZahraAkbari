package com.asellion.product.controller;

import com.asellion.product.service.ProductService;
import com.asellion.product.dto.ProductDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Rest controller class to start a new game and move on the game.
 *
 * @author Zahra Akbari (akbari.zahra@gmail.com)
 */

@RestController
@RequestMapping(value = "/api", produces = { MediaType.APPLICATION_JSON_VALUE })
@Api(description = "Provides set of endpoints to manage product.")
public class ProductController {

    @Autowired
    private ProductService productService;
    private Logger logger = LoggerFactory.getLogger(ProductController.class);

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/products")
    @ApiOperation("Create a new product")
    public ProductDto createProduct(@RequestBody ProductDto newProduct) {
        logger.info("Create a new Product");
        ProductDto gameDto = productService.createProduct(newProduct);
        logger.info("A new product successfully created");
        return gameDto;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/products")
    @ApiOperation("Get a list of products")
    public List<ProductDto> getAllProducts() {
        return productService.getAllProducts();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/products/{id}")
    @ApiOperation("Get one product from the list")
    public ProductDto getProduct(@PathVariable int id) {
        return productService.findProductById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/products/{id}" ,consumes =  MediaType.APPLICATION_JSON_VALUE, produces =
            MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Update a product from the list")
    public  ProductDto updateProduct(@ApiParam(name = "id", value = "id to update", required = true) @PathVariable int id , @ApiParam(name = "product", value = "product to update", required = true)@Valid @RequestBody ProductDto product ) {
        ProductDto productDto = productService.updateProduct(product, id);
        return productDto;
    }

}
