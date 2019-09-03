package com.whitecloak.fpsales;

import com.whitecloak.fpsales.service.SalesService;
import com.whitecloak.fpsales.service.SalesServiceImpl;

import java.math.BigDecimal;
import java.time.Month;

public class Application {

    public static void main(String[] args) throws Exception {
        SalesService salesService = new SalesServiceImpl();

        System.out.println("\n>>>>> Print all item names");
        salesService.listAllItemNames()
            .forEach(System.out::println);

        System.out.println("\n>>>>> Total sales each branch");
        salesService.computeTotalSalesEachBranch()
            .forEach(total -> System.out.printf("%s: %s\n", total.getBranch(), total.getTotalSales()));

        System.out.println("\n>>>>> Total sales all branches");
        BigDecimal totalSalesAllBranch = salesService.computeTotalSalesAllBranch();
        System.out.println(totalSalesAllBranch);

        System.out.println("\n>>>>> Total sales all branches 2016");
        BigDecimal totalSalesAllBranch2016 = salesService.computeTotalSalesAllBranchByYear(2016);
        System.out.println(totalSalesAllBranch2016);

        System.out.println("\n>>>>> Trending month of item Fruits");
        Month trendingMonth = salesService.showTrendingMonthByItem("Fruits");
        System.out.println(trendingMonth);

        System.out.println("\n>>>>> Trending item of 2012");
        String trendingItem2012 = salesService.showTrendingItemByYear(2012);
        System.out.println(trendingItem2012);

        System.out.println("\n>>>>> Trending item by sales count");
        String item = salesService.showTrendingItem();
        System.out.println(item);

        System.out.println("\n>>>>> Trending item per branch manila");
        String trendingItemManila = salesService.showTrendingItemByBranch("manila");
        System.out.println(trendingItemManila);

        System.out.println("\n>>>>> Trending item per branch cebu");
        String trendingItemCebu = salesService.showTrendingItemByBranch("cebu");
        System.out.println(trendingItemCebu);

        System.out.println("\n>>>>> Trending item per branch davao");
        String trendingItemDavao = salesService.showTrendingItemByBranch("davao");
        System.out.println(trendingItemDavao);
    }
}
