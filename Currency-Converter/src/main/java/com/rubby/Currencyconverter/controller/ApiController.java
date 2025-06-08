package com.rubby.Currencyconverter.controller;

import com.rubby.Currencyconverter.model.ConversionRequest;
import com.rubby.Currencyconverter.model.ConversionResult;
import com.rubby.Currencyconverter.model.Currency;
import com.rubby.Currencyconverter.service.ConversionService;
import com.rubby.Currencyconverter.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private ConversionService conversionService;

    @GetMapping("/currencies")
    public ResponseEntity<List<Currency>> getCurrencies() {
        return ResponseEntity.ok(currencyService.getSupportedCurrencies());
    }

    @PostMapping("/convert")
    public ResponseEntity<ConversionResult> convertCurrency(@Valid @RequestBody ConversionRequest request) {
        try {
            ConversionResult result = conversionService.convertCurrency(request);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/convert")
    public ResponseEntity<ConversionResult> convertCurrencyGet(
            @RequestParam Double amount,
            @RequestParam String from,
            @RequestParam String to) {

        try {
            ConversionRequest request = new ConversionRequest(amount, from, to);
            ConversionResult result = conversionService.convertCurrency(request);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
