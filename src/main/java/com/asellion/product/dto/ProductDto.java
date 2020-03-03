package com.asellion.product.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Product Dto class
 *
 * @author Zahra Akbari (akbari.zahra@gmail.com)
 */
public class ProductDto implements Serializable {
    private int id;
    private String name;
    private double currentPrice;
    private Timestamp lastUpdate;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
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
        ProductDto productDto = (ProductDto) that;
        return new EqualsBuilder()
                .append(this.id, productDto.id)
                .append(this.name, productDto.name)
                .append(this.currentPrice, productDto.currentPrice)
                .append(this.lastUpdate, productDto.lastUpdate)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(id)
                .append(name)
                .append(currentPrice)
                .append(lastUpdate)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", currentPrice=" + currentPrice + ", lastUpdate=" + lastUpdate  + "]";
    }
}
