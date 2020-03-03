package com.asellion.product.exception;

import org.springframework.http.HttpStatus;

/**
 * Enumeration of product project error codes.
 *
 * @author Zahra Akbari (akbari.zahra@gmail.com)
 */

public enum ErrorCode {
    PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND),
    INVALID_MOVEMENT(HttpStatus.BAD_REQUEST);

    private final HttpStatus value;

    ErrorCode(HttpStatus value) {
        this.value = value;
    }

    public HttpStatus getValue() {
        return value;
    }
}