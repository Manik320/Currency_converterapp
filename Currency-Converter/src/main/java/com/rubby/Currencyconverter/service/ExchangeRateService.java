package com.rubby.Currencyconverter.service;

import com.rubby.Currencyconverter.model.ExchangeRateResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Service
public class ExchangeRateService {

    private static final Logger logger = LoggerFactory.getLogger(ExchangeRateService.class);
    private final WebClient webClient;

    @Value("${exchange.api.url:https://api.exchangerate-api.com/v4/latest}")
    private String apiUrl;

    // Fallback rates for demo purposes
    private final Map<String, Double> fallbackRates = createFallbackRates();

    public ExchangeRateService() {
        this.webClient = WebClient.builder()
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(1024 * 1024))
                .build();
    }

    public Double getExchangeRate(String fromCurrency, String toCurrency) {
        if (fromCurrency.equals(toCurrency)) {
            return 1.0;
        }

        try {
            ExchangeRateResponse response = fetchExchangeRates(fromCurrency)
                    .block();

            if (response != null && response.isSuccess() && response.getRates() != null) {
                return response.getRates().get(toCurrency);
            }
        } catch (Exception e) {
            logger.warn("Failed to fetch live exchange rates, using fallback rates", e);
        }

        // Use fallback rates
        return getFallbackRate(fromCurrency, toCurrency);
    }

    private Mono<ExchangeRateResponse> fetchExchangeRates(String baseCurrency) {
        String url = apiUrl + "/" + baseCurrency;

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(ExchangeRateResponse.class)
                .doOnError(error -> logger.error("Error fetching exchange rates: {}", error.getMessage()));
    }

    private Double getFallbackRate(String fromCurrency, String toCurrency) {
        String key = fromCurrency + "_" + toCurrency;
        Double rate = fallbackRates.get(key);

        if (rate != null) {
            return rate;
        }

        // Try inverse rate
        String inverseKey = toCurrency + "_" + fromCurrency;
        Double inverseRate = fallbackRates.get(inverseKey);
        if (inverseRate != null && inverseRate != 0) {
            return 1.0 / inverseRate;
        }

        // Default fallback
        return 1.0;
    }

    private Map<String, Double> createFallbackRates() {
        Map<String, Double> rates = new HashMap<>();

        // USD base rates
        rates.put("USD_EUR", 0.85);
        rates.put("USD_GBP", 0.73);
        rates.put("USD_JPY", 110.0);
        rates.put("USD_AUD", 1.35);
        rates.put("USD_CAD", 1.25);
        rates.put("USD_CHF", 0.92);
        rates.put("USD_CNY", 6.45);
        rates.put("USD_SEK", 8.75);
        rates.put("USD_NZD", 1.42);
        rates.put("USD_MXN", 20.1);
        rates.put("USD_SGD", 1.35);
        rates.put("USD_HKD", 7.8);
        rates.put("USD_NOK", 8.5);
        rates.put("USD_TRY", 8.2);
        rates.put("USD_RUB", 74.5);
        rates.put("USD_INR", 74.2);
        rates.put("USD_BRL", 5.2);
        rates.put("USD_ZAR", 14.8);
        rates.put("USD_KRW", 1180.0);

        // EUR base rates
        rates.put("EUR_GBP", 0.86);
        rates.put("EUR_JPY", 129.4);
        rates.put("EUR_USD", 1.18);

        // Add more cross rates as needed
        rates.put("GBP_USD", 1.37);
        rates.put("GBP_EUR", 1.16);

        return rates;
    }
}
