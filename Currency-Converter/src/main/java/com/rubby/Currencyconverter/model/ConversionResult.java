package com.rubby.Currencyconverter.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ConversionResult {
    private Double amount;
    private String fromCurrency;
    private String toCurrency;
    private Double convertedAmount;
    private Double exchangeRate;
    private String date;

    public ConversionResult() {}

    public ConversionResult(Double amount, String fromCurrency, String toCurrency,
                            Double convertedAmount, Double exchangeRate, String date) {
        this.amount = amount;
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.convertedAmount = convertedAmount;
        this.exchangeRate = exchangeRate;
        this.date = date;
    }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public String getFromCurrency() { return fromCurrency; }
    public void setFromCurrency(String fromCurrency) { this.fromCurrency = fromCurrency; }

    public String getToCurrency() { return toCurrency; }
    public void setToCurrency(String toCurrency) { this.toCurrency = toCurrency; }

    public Double getConvertedAmount() { return convertedAmount; }
    public void setConvertedAmount(Double convertedAmount) { this.convertedAmount = convertedAmount; }

    public Double getExchangeRate() { return exchangeRate; }
    public void setExchangeRate(Double exchangeRate) { this.exchangeRate = exchangeRate; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getFormattedAmount() {
        return formatCurrency(amount);
    }

    public String getFormattedConvertedAmount() {
        return formatCurrency(convertedAmount);
    }

    public String getFormattedExchangeRate() {
        return formatCurrency(exchangeRate);
    }

    private String formatCurrency(Double value) {
        if (value == null) return "0.00";
        return BigDecimal.valueOf(value)
                .setScale(2, RoundingMode.HALF_UP)
                .toString();
    }
}