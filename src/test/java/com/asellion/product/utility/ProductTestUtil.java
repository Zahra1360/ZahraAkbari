package com.asellion.product.utility;

import com.asellion.product.dto.ProductDto;
import com.asellion.product.entity.Product;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class prepare all needed objects to test;
 *
 * @author Zahra Akbari (akbari.zahra@gmail.com)
 */

public final class ProductTestUtil {
    public static final double CURRENT_PRICE_1 = 600;
    public static final double CURRENT_PRICE_2 = 10;
    public static final String NAME = "Product 1";
    private ProductTestUtil() {
    }

    public static Product getProduct() {
        Product product = new Product();
        product.setName(NAME);
        product.setCurrentPrice(CURRENT_PRICE_1);

        Date date= new Date();
        long time = date.getTime();
        product.setLastUpdate(date);
        return product;
    }

    public static ProductDto getProductDto() {
        return getProduct().getLightDto();
    }

    public static List<ProductDto> getProductDtoList() {
        List<ProductDto> productDtoList = new ArrayList<>();
        productDtoList.add(getProductDto());
        ProductDto anotherProduct= getProductDto();
        anotherProduct.setCurrentPrice(CURRENT_PRICE_2);
        productDtoList.add(anotherProduct);
        return productDtoList;
    }
    public static List<Product> getProductList() {
        List<Product> productDtoList = new ArrayList<>();
        productDtoList.add(getProduct());
        Product anotherProduct= getProduct();
        anotherProduct.setCurrentPrice(CURRENT_PRICE_2);
        anotherProduct.setId(2);
        productDtoList.add(anotherProduct);
        return productDtoList;
    }

}
