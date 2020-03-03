package com.asellion.product.controller;

import com.asellion.product.dto.ProductDto;
import com.asellion.product.exception.AsellionServiceException;
import com.asellion.product.exception.ErrorCode;
import com.asellion.product.service.ProductService;

import com.asellion.product.utility.ProductTestUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * This class test product management rest controllers;
 *
 * @author Zahra Akbari (akbari.zahra@gmail.com)
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    @MockBean
    private ProductService productService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void create_whenCreatNewPoduct_returnNewProduct() throws Exception {
        ProductDto productDto = ProductTestUtil.getProductDto();
        when(productService.createProduct(productDto)).thenReturn(productDto);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(productDto);
        MvcResult mvcResult = mockMvc.perform(post("/api/products")
                .contentType(APPLICATION_JSON).content(requestJson))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("name", is(productDto.getName())))
                .andExpect(jsonPath("currentPrice", is(productDto.getCurrentPrice()))).andReturn();

        Assert.assertEquals("application/json",
                mvcResult.getResponse().getContentType());
        verify(productService, times(1)).createProduct(productDto);
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void update_whenUpdateProduct_returnUpdatedProduct() throws Exception {
        ProductDto productDto = ProductTestUtil.getProductDto();
        when(productService.updateProduct(productDto, 1)).thenReturn(productDto);
        productDto.setCurrentPrice(ProductTestUtil.CURRENT_PRICE_1 * 2);
         mockMvc.perform(MockMvcRequestBuilders
                .put("/api/products/{id}", 1)
                .content(asJsonString(productDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("name").value(productDto.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("currentPrice").value(ProductTestUtil.CURRENT_PRICE_1 * 2));

        verify(productService, times(1)).updateProduct(productDto, 1);
    }

    @Test
    public void findAll_whenFoundProdcuts_returnAllProducts() throws Exception {
        when(productService.getAllProducts()).thenReturn(ProductTestUtil.getProductDtoList());

        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is(ProductTestUtil.NAME)))
                .andExpect(jsonPath("$[0].currentPrice", is(ProductTestUtil.CURRENT_PRICE_1)))
                .andExpect(jsonPath("$[1].name", is(ProductTestUtil.NAME)))
                .andExpect(jsonPath("$[1].currentPrice", is(ProductTestUtil.CURRENT_PRICE_2)));

        verify(productService, times(1)).getAllProducts();
        verifyNoMoreInteractions(productService);
    }

    @Test
    public void find_whenFoundOneProdcut_returnOneProduct() throws Exception {
        ProductDto productDto = ProductTestUtil.getProductDto();
        when(productService.findProductById(productDto.getId())).thenReturn(productDto);

        mockMvc.perform(get("/api/products/{id}", productDto.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("name", is(ProductTestUtil.NAME)))
                .andExpect(jsonPath("currentPrice", is(ProductTestUtil.CURRENT_PRICE_1)));

        verify(productService, times(1)).findProductById(productDto.getId());
        verifyNoMoreInteractions(productService);
    }

    @Test
    public void findById_entryNotFound_ShouldReturnHttpStatusCode404() throws Exception {
        when(productService.findProductById(anyInt())).thenThrow(new AsellionServiceException(ErrorCode.PRODUCT_NOT_FOUND, "Product not found"));

        mockMvc.perform(get("/api/products/{id}", anyInt()))
                .andExpect(status().isNotFound());

        verify(productService, times(1)).findProductById(anyInt());
        verifyNoMoreInteractions(productService);
    }
}
