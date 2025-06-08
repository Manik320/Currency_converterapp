package com.rubby.Currencyconverter.service;

import com.rubby.Currencyconverter.model.ConversionRequest;
import com.rubby.Currencyconverter.model.ConversionResult;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ConversionServiceTest {

    @Mock
    private ExchangeRateService exchangeRateService;

    @InjectMocks
    private ConversionService conversionService;

    @Test
    void testConvertCurrency() {
        // Given
        ConversionRequest request = new ConversionRequest(100.0, "USD", "EUR");
        when(exchangeRateService.getExchangeRate("USD", "EUR")).thenReturn(0.85);

        // When
        ConversionResult result = conversionService.convertCurrency(request);

        // Then
        assertNotNull(result);
        assertEquals(100.0, result.getAmount());
        assertEquals("USD", result.getFromCurrency());
        assertEquals("EUR", result.getToCurrency());
        assertEquals(85.0, result.getConvertedAmount());
        assertEquals(0.85, result.getExchangeRate());
    }

    @Test
    void testConvertCurrencyWithNullRate() {
        // Given
        ConversionRequest request = new ConversionRequest(100.0, "USD", "XYZ");
        when(exchangeRateService.getExchangeRate("USD", "XYZ")).thenReturn(null);

        // When & Then
        assertThrows(RuntimeException.class, () -> conversionService.convertCurrency(request));
    }
}
