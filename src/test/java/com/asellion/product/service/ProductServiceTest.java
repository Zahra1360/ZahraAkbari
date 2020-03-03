package com.asellion.product.service;

import com.asellion.product.dto.ProductDto;
import com.asellion.product.exception.AsellionServiceException;
import com.asellion.product.utility.ProductTestUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.mockito.ArgumentMatchers.anyInt;

/**
 * This class test ProductService' methods with different parameters;
 *
 * @author Zahra Akbari (akbari.zahra@gmail.com)
 */

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Test
    public void createProduct_whenCalled_returnsNewProductInstance() {
        ProductDto productDto = productService.createProduct(ProductTestUtil.getProductDto());
        Assert.assertThat(productDto, notNullValue());
        Assert.assertThat(productDto.getName(), is(ProductTestUtil.NAME));
        Assert.assertThat(productDto.getCurrentPrice(), is(ProductTestUtil.CURRENT_PRICE_1));
    }

    @Test
    public void getAllProducts_whenCalled_returnsProductsList() {
        //given
        productService.createProduct(ProductTestUtil.getProductDto());
        productService.createProduct(ProductTestUtil.getProductDto());
        //when
        List<ProductDto> productDto = productService.getAllProducts();
        //then
        Assert.assertThat(productDto, notNullValue());
        Assert.assertNotEquals(productDto.size(), 0);
    }

    @Test
    public void findProduct_whenNotFound_shouldThrowException() {
        //given
        int id = anyInt();
        //then
        Assertions.assertThrows(AsellionServiceException.class, () -> {
            //when
            productService.findProductById(id);
        });
    }

    @Test
    public void findProduct_whenCalled_returnsOneProduct() {
        //given
        ProductDto product = productService.createProduct(ProductTestUtil.getProductDto());

        //when
        ProductDto productDto = productService.findProductById(product.getId());
        //then
        Assert.assertThat(productDto, notNullValue());
        Assert.assertThat(productDto.getName(), is(ProductTestUtil.NAME));
        Assert.assertThat(productDto.getCurrentPrice(), is(ProductTestUtil.CURRENT_PRICE_1));
    }

    @Test
    public void updateProduct_whenCalled_returnsUpdatedProduct() {
        //given
        ProductDto product = productService.createProduct(ProductTestUtil.getProductDto());

        //when
        product.setCurrentPrice(ProductTestUtil.CURRENT_PRICE_2);
        ProductDto productDto = productService.updateProduct(product,product.getId() );

        //then
        Assert.assertThat(productDto.getId(), is(product.getId()));
        Assert.assertThat(productDto.getName(), is(product.getName()));
        Assert.assertThat(productDto.getCurrentPrice(), is(product.getCurrentPrice()));
    }

    @Test
    public void updateProduct_whenNotFoundProduct_shouldThrowException() {
        //given
        ProductDto productForUpdate = ProductTestUtil.getProductDto();
        //then
        Assertions.assertThrows(AsellionServiceException.class, () -> {
            //when

            productForUpdate.setCurrentPrice(ProductTestUtil.CURRENT_PRICE_2);
            productService.updateProduct(productForUpdate, anyInt());
        });

    }

}
