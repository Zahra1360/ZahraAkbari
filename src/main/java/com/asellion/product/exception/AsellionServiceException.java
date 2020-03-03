package com.asellion.product.exception;

import java.util.Optional;

/**
 * custome exception for product project.
 *
 * @author Zahra Akbari (akbari.zahra@gmail.com)
 */
public class AsellionServiceException extends RuntimeException {

    private final Optional<ErrorCode> errorCode;

    /**
     * Constructs a new exception with the specified error code and detail message.
     *
     * @param errorCode the error code
     * @param message   the detail message
     */
    public AsellionServiceException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = Optional.ofNullable(errorCode);
    }

    public Optional<ErrorCode> getErrorCode() {
        return errorCode;
    }
}
