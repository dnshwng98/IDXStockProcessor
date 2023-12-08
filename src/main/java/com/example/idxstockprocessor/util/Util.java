package com.example.idxstockprocessor.util;

import com.example.idxstockprocessor.model.DailyStockTransaction;
import com.example.idxstockprocessor.model.Stock;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class Util {
    public List<DailyStockTransaction> retrieveTodayStockTransaction() throws Exception {
        try {
            // Retrieve today stock transaction
            List<DailyStockTransaction> todayStockTransactions = new ArrayList<>();

            return todayStockTransactions;
        } catch (Exception e) {
            throw new Exception("Failed to retrieve today stock transaction...");
        }
    }

    public void calculateDailyReturn(DailyStockTransaction dailyStockTransaction) throws Exception {
        try {
            dailyStockTransaction.getStock().setDailyReturn(
                    ((double) (dailyStockTransaction.getChange()) / dailyStockTransaction.getOpen()) * 100);
        } catch (Exception e) {
            throw new Exception("Failed to calculate daily return...");
        }
    }

    public double calculateTotalReturn(List<DailyStockTransaction> todayStockTransactions) throws Exception {
        try {
            double totalReturn = 0.0;

            for (DailyStockTransaction dailyStockTransaction : todayStockTransactions) {
                calculateDailyReturn(dailyStockTransaction);
                totalReturn += dailyStockTransaction.getStock().getDailyReturn();
            }

            return totalReturn;
        } catch (Exception e) {
            throw new Exception("Failed to calculate total return...");
        }
    }

    public List<Stock> calculateRelativePerformance(List<DailyStockTransaction> todayStockTransactions
            , double averageReturn) throws Exception {
        try {
            List<Stock> updatedStock = new ArrayList<>();
            for (DailyStockTransaction dailyStockTransaction : todayStockTransactions) {
                dailyStockTransaction.getStock().setRelativeDailyPerformance(
                        ((dailyStockTransaction.getStock().getDailyReturn() - averageReturn) / averageReturn) * 100);

                updatedStock.add(dailyStockTransaction.getStock());
            }

            return updatedStock;
        } catch (Exception e) {
            throw new Exception("Failed to calculate relative performance...");
        }
    }

    public List<DailyStockTransaction> mapResponseToDailyStockTransaction(List<Map<String,String>> response) {
        List<DailyStockTransaction> dailyStockTransactions = new ArrayList<>();

        for(Map<String, String> object : response) {
            Stock stock = new Stock();
            stock.setCode(object.get("code"));
            stock.setName(object.get("name"));

            DailyStockTransaction dailyStockTransaction = new DailyStockTransaction();
            dailyStockTransaction.setStock(stock);
            dailyStockTransaction.setOpen(Integer.parseInt(object.get("open")));
            dailyStockTransaction.setClose(Integer.parseInt(object.get("close")));
            dailyStockTransaction.setChange(Integer.parseInt(object.get("change")));
            dailyStockTransaction.setChangePercentage(Float.parseFloat(object.get("change_percentage")));

            dailyStockTransactions.add(dailyStockTransaction);
        }

        return dailyStockTransactions;
    }
}
