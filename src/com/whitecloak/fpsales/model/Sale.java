package com.whitecloak.fpsales.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Sale {
    private String itemName;
    private LocalDate date;
    private int unitSold;
    private BigDecimal unitPrice;

    public Sale() {}

    public Sale(String itemName, LocalDate date, int unitSold, BigDecimal unitPrice) {
        this.itemName = itemName;
        this.date = date;
        this.unitSold = unitSold;
        this.unitPrice = unitPrice;
    }

    private final static DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("M/d/yyyy");

    public static Sale fromCsv(String csv) {
        String[] data = csv.split(",");
        Sale sale = new Sale();
        sale.itemName = data[0];
        sale.date = LocalDate.parse(data[1], DATE_FORMAT);
        sale.unitSold = Integer.parseInt(data[2]);
        sale.unitPrice = new BigDecimal(data[3]);

        return sale;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getUnitSold() {
        return unitSold;
    }

    public void setUnitSold(int unitSold) {
        this.unitSold = unitSold;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getTotalPrice() {
        return unitPrice.multiply(new BigDecimal(unitSold));
    }
}
