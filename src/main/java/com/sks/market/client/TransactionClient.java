package com.sks.market.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "transaction", url = "http://localhost:8883/")
public interface TransactionClient {
    @GetMapping("/stockList")
    public List<String> getStockList();
}
