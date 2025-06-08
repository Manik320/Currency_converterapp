package com.rubby.Currencyconverter.service;

import com.rubby.Currencyconverter.model.Currency;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;

@Service
public class CurrencyService {

    public List<Currency> getSupportedCurrencies() {
        return Arrays.asList(
                new Currency("USD", "US Dollar", "$"),
                new Currency("EUR", "Euro", "€"),
                new Currency("GBP", "British Pound", "£"),
                new Currency("JPY", "Japanese Yen", "¥"),
                new Currency("AUD", "Australian Dollar", "A$"),
                new Currency("CAD", "Canadian Dollar", "C$"),
                new Currency("CHF", "Swiss Franc", "CHF"),
                new Currency("CNY", "Chinese Yuan", "¥"),
                new Currency("SEK", "Swedish Krona", "kr"),
                new Currency("NZD", "New Zealand Dollar", "NZ$"),
                new Currency("MXN", "Mexican Peso", "$"),
                new Currency("SGD", "Singapore Dollar", "S$"),
                new Currency("HKD", "Hong Kong Dollar", "HK$"),
                new Currency("NOK", "Norwegian Krone", "kr"),
                new Currency("TRY", "Turkish Lira", "₺"),
                new Currency("RUB", "Russian Ruble", "₽"),
                new Currency("INR", "Indian Rupee", "₹"),
                new Currency("BRL", "Brazilian Real", "R$"),
                new Currency("ZAR", "South African Rand", "R"),
                new Currency("KRW", "South Korean Won", "₩")
        );
    }

    public Currency getCurrencyByCode(String code) {
        return getSupportedCurrencies().stream()
                .filter(currency -> currency.getCode().equals(code))
                .findFirst()
                .orElse(new Currency(code, code, code));
    }
}
