package com.sks.market.controller;

import com.sks.market.service.MarketService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@Data
@RequiredArgsConstructor
@CrossOrigin
public class MarketController {
    private final MarketService marketService;

    @GetMapping("/isMarketUp")
    public String isMarketUp(){
        return "Market is Open!!";
    }

    @PostMapping("/stocksPrice")
    public Map<String,Float> getStockPrice(@RequestBody List<String> stocks){
        return marketService.getStockPrice(stocks);
    }

    @GetMapping("/stockList")
    public List<String> getStockList(){
        return marketService.getStockList();
    }

    @GetMapping("/companyList")
    public Map<String,Float> companyList() throws IOException {
        return marketService.getCompanyList();
    }
}
