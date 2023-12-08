package com.example.idxstockprocessor.service;

import com.example.idxstockprocessor.model.DailyStockTransaction;
import com.example.idxstockprocessor.model.Stock;
import com.example.idxstockprocessor.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RelativePerformanceService {
    @Autowired
    Util util;

    @Autowired
    RepositoryService repositoryService;

    public String calculateAndUpdateRelativePerformance(String datetime) {
        try {
            List<DailyStockTransaction> todayStockTransactions = repositoryService.retrieveDailyStockTransactionBasedOnDate(datetime);

            double totalReturn = util.calculateTotalReturn(todayStockTransactions);
            double averageReturn = totalReturn / todayStockTransactions.size();

            // Collect stock data separately
            List<Stock> updatedStockData = util.calculateRelativePerformance(todayStockTransactions, averageReturn);

            // Update Stock data
            repositoryService.updateAllStockData(updatedStockData);

            return "Calculate and update relative performance is done...";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
