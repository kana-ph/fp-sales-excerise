package com.whitecloak.fpsales.model;

import java.math.BigDecimal;

public class BranchTotalSales {

    private String branch;
    private BigDecimal totalSales;

    public BranchTotalSales(String branch, BigDecimal totalSales) {
        this.branch = branch;
        this.totalSales = totalSales;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public BigDecimal getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(BigDecimal totalSales) {
        this.totalSales = totalSales;
    }
}
