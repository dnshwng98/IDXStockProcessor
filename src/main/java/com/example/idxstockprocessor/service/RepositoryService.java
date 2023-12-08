package com.example.idxstockprocessor.service;

import com.example.idxstockprocessor.model.DailyStockTransaction;
import com.example.idxstockprocessor.model.Stock;
import com.example.idxstockprocessor.util.Util;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class RepositoryService {
    @Autowired
    Util util;

    public List<DailyStockTransaction> retrieveDailyStockTransactionBasedOnDate(String datetime) throws Exception {
        try {
            String url = "http://localhost:8000/DataManagementAPI/retrieveDailyStockTransactionBasedOnDate";
            RestTemplate restTemplate = new RestTemplate();
            System.out.println("29");
            String response = restTemplate.postForObject(url, datetime, String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            System.out.println("32");
            List<Map<String, String>> reformattedResponse = objectMapper.readValue(response, new TypeReference<List<Map<String, String>>>() {});
            return util.mapResponseToDailyStockTransaction(reformattedResponse);
        } catch (RestClientException e) {
            throw new Exception("Failed to save daily stock transaction data......");
        }
    }

    public void updateAllStockData(List<Stock> stocks) throws Exception {
        try {
            String url = "http://127.0.0.1:8000/DataManagementAPI/updateAllStockData";
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.postForObject(url, stocks, String.class);
        } catch (RestClientException e) {
            throw new Exception("Failed to update all stock data...");
        }
    }
}
