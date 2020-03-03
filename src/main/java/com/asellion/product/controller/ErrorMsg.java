package com.asellion.product.controller;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;

/**
 * error message class
 *
 * @author Zahra Akbari (akbari.zahra@gmail.com)
 */

@JsonPropertyOrder({"errorCode", "errorMessage"})
public class ErrorMsg implements Serializable {

    private int errorCode;
    private String errorMessage;

    public ErrorMsg(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public boolean equals(Object that) {
        if (that == null) {
            return false;
        }
        if (that == this) {
            return true;
        }
        if (that.getClass() != getClass()) {
            return false;
        }
        ErrorMsg errorMsg = (ErrorMsg) that;
        return new EqualsBuilder()
                .appendSuper(super.equals(that))
                .append(this.errorCode, errorMsg.errorCode)
                .append(this.errorMessage, errorMsg.errorMessage)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(errorCode)
                .append(errorMessage)
                .toHashCode();
    }
}
