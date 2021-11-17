package com.surjo.sheetloader;

import java.math.BigDecimal;

/**
 * @author sanjoy
 * on 11/16/21
 */

public class MonthlyExpense {
    private Long count;
    private String description;
    private BigDecimal cost;

    public MonthlyExpense(Long count, String description, BigDecimal cost) {
        this.count = count;
        this.description = description;
        this.cost = cost;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }
}
