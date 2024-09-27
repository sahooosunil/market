package com.sks.market.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sks.market.client.NiftyClient;
import com.sks.market.client.TransactionClient;
import com.sks.market.util.Utility;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@Getter
public class MarketService {
    private final NiftyClient niftyClient;
    private final TransactionClient transactionClient;
    private final List<String> availableStocks;
    private final Utility utility;
    private List<Map<String, Object>> mapList;

    public MarketService(NiftyClient niftyClient, TransactionClient transactionClient, Utility utility) {
        this.niftyClient = niftyClient;
        this.transactionClient = transactionClient;
        this.availableStocks = new ArrayList<>();
        this.utility = utility;
    }


    public Map<String, Float> getStockPrice(List<String> stocks) {
        Float niftyPrice = niftyClient.getNiftyPrice();
        Float senSexPrice = niftyClient.getSenSexPrice();
        Float avg = (niftyPrice + senSexPrice) / 2;
        return stocks.stream().collect(Collectors.toMap(key -> key, value -> getStockPrice(avg)));
    }

    public List<String> getStockList()  {
        return transactionClient.getStockList();
    }

    public Map<String, Float> getCompanyList() throws IOException {
        String json = utility.readFile("/bse.json");
        ObjectMapper objectMapper = new ObjectMapper();
        List<Map> mapList = objectMapper.readValue(json, List.class);
        return mapList.stream().collect(Collectors.toMap(ele -> ele.get("company").toString(), ele -> Float.valueOf(ele.get("price").toString())));
    }

    private Float getStockPrice(Float avg) {
        Float random = 100 + new Random().nextFloat() * (550 - 450);
        return (random + avg) / 2;
    }


}
