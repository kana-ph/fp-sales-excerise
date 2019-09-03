package com.whitecloak.fpsales.service;

import com.whitecloak.fpsales.model.BranchTotalSales;
import com.whitecloak.fpsales.model.Sale;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Month;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Comparator.comparingLong;
import static java.util.stream.Collectors.*;

public class SalesServiceImpl implements SalesService {

    private final static Path ROOT_CSV_DIRECTORY = Path.of("sales");

    @Override
    public SortedSet<String> listAllItemNames() throws IOException {
        return Files.walk(ROOT_CSV_DIRECTORY)
            .filter(Files::isRegularFile)
            .flatMap(this::streamFile)
            .map(Sale::fromCsv)
            .map(Sale::getItemName)
            .collect(Collectors.toCollection(TreeSet::new));
    }

    @Override
    public BigDecimal computeTotalSalesAllBranch() throws IOException {
        return Files.walk(ROOT_CSV_DIRECTORY)
            .filter(Files::isRegularFile)
            .flatMap(this::streamFile)
            .map(Sale::fromCsv)
            .map(Sale::getTotalPrice)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public List<BranchTotalSales> computeTotalSalesEachBranch() throws IOException {
        return Files.walk(ROOT_CSV_DIRECTORY)
            .filter(Files::isRegularFile)
            .map(file -> {
                String branchName = file.getFileName()
                    .toString()
                    .replace(".csv", "");
                BigDecimal totalSales = streamFile(file)
                    .map(Sale::fromCsv)
                    .map(Sale::getTotalPrice)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
                return new BranchTotalSales(branchName, totalSales);
            })
            .collect(toList());
    }

    @Override
    public BigDecimal computeTotalSalesAllBranchByYear(int year) throws IOException {
        return Files.walk(ROOT_CSV_DIRECTORY)
            .filter(Files::isRegularFile)
            .flatMap(this::streamFile)
            .map(Sale::fromCsv)
            .filter(sale -> year == sale.getDate().getYear())
            .map(Sale::getTotalPrice)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public String showTrendingItem() throws IOException {
        return Files.walk(ROOT_CSV_DIRECTORY)
            .filter(Files::isRegularFile)
            .flatMap(this::streamFile)
            .map(Sale::fromCsv)
            .collect(groupingBy(Sale::getItemName, summingInt(Sale::getUnitSold)))
            .entrySet()
            .stream()
            .max(comparingLong(Map.Entry::getValue))
            .map(Map.Entry::getKey)
            .orElse("");
    }

    @Override
    public String showTrendingItemByBranch(String branchName) throws IOException {
        String filename = branchName + ".csv";
        return Files.walk(ROOT_CSV_DIRECTORY)
            .filter(Files::isRegularFile)
            .filter(file -> filename.equals(file.getFileName().toString()))
            .flatMap(this::streamFile)
            .map(Sale::fromCsv)
            .collect(groupingBy(Sale::getItemName, summingInt(Sale::getUnitSold)))
            .entrySet()
            .stream()
            .max(comparingLong(Map.Entry::getValue))
            .map(Map.Entry::getKey)
            .orElse("");
    }

    @Override
    public String showTrendingItemByYear(int year) throws IOException {
        return Files.walk(ROOT_CSV_DIRECTORY)
            .filter(Files::isRegularFile)
            .flatMap(this::streamFile)
            .map(Sale::fromCsv)
            .filter(sale -> year == sale.getDate().getYear())
            .collect(groupingBy(Sale::getItemName, summingInt(Sale::getUnitSold)))
            .entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .map(Map.Entry::getKey)
            .orElse(null);
    }

    @Override
    public Month showTrendingMonthByItem(String itemName) throws IOException {
        return Files.walk(ROOT_CSV_DIRECTORY)
            .filter(Files::isRegularFile)
            .flatMap(this::streamFile)
            .map(Sale::fromCsv)
            .filter(sale -> itemName.equals(sale.getItemName()))
            .collect(groupingBy(computeMapKey(), summingInt(Sale::getUnitSold)))
            .entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .map(Map.Entry::getKey)
            .orElse(null);
    }

    private Stream<String> streamFile(Path path) {
        try {
            return Files.lines(path);
        } catch (IOException e) {
            e.printStackTrace(System.err);
            return Stream.empty();
        }
    }

    private Function<Sale, Month> computeMapKey() {
        return sale -> sale.getDate().getMonth();
    }
}
