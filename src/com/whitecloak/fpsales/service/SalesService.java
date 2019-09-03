package com.whitecloak.fpsales.service;

import com.whitecloak.fpsales.model.BranchTotalSales;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Month;
import java.util.List;
import java.util.SortedSet;

public interface SalesService {

    SortedSet<String> listAllItemNames() throws IOException;

    BigDecimal computeTotalSalesAllBranch() throws IOException;

    List<BranchTotalSales> computeTotalSalesEachBranch() throws IOException;

    BigDecimal computeTotalSalesAllBranchByYear(int year) throws IOException;

    String showTrendingItem() throws IOException;

    String showTrendingItemByBranch(String branchName) throws IOException;

    String showTrendingItemByYear(int year) throws IOException;

    Month showTrendingMonthByItem(String itemName) throws IOException;
}
