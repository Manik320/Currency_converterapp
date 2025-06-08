package com.rubby.Currencyconverter.service;

import com.rubby.Currencyconverter.model.ConversionRequest;
import com.rubby.Currencyconverter.model.ConversionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class ConversionService {

    @Autowired
    private ExchangeRateService exchangeRateService;

    public ConversionResult convertCurrency(ConversionRequest request) {
        Double exchangeRate = exchangeRateService.getExchangeRate(
                request.getFromCurrency(),
                request.getToCurrency()
        );

        if (exchangeRate == null) {
            throw new RuntimeException("Unable to get exchange rate for " +
                    request.getFromCurrency() + " to " + request.getToCurrency());
        }

        Double convertedAmount = request.getAmount() * exchangeRate;
        String currentDate = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);

        return new ConversionResult(
                request.getAmount(),
                request.getFromCurrency(),
                request.getToCurrency(),
                convertedAmount,
                exchangeRate,
                currentDate
        );
    }
}