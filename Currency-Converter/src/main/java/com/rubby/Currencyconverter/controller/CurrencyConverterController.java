package com.rubby.Currencyconverter.controller;

import com.rubby.Currencyconverter.model.ConversionRequest;
import com.rubby.Currencyconverter.model.ConversionResult;
import com.rubby.Currencyconverter.model.Currency;
import com.rubby.Currencyconverter.service.ConversionService;
import com.rubby.Currencyconverter.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import java.util.List;

@Controller
public class CurrencyConverterController {

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private ConversionService conversionService;

    @GetMapping("/")
    public String index(Model model) {
        List<Currency> currencies = currencyService.getSupportedCurrencies();
        model.addAttribute("currencies", currencies);
        model.addAttribute("conversionRequest", new ConversionRequest());
        return "index";
    }

    @PostMapping("/convert")
    public String convertCurrency(@Valid @ModelAttribute("conversionRequest") ConversionRequest request,
                                  BindingResult bindingResult,
                                  Model model,
                                  RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            List<Currency> currencies = currencyService.getSupportedCurrencies();
            model.addAttribute("currencies", currencies);
            model.addAttribute("error", "Please correct the errors below.");
            return "index";
        }

        try {
            ConversionResult result = conversionService.convertCurrency(request);
            model.addAttribute("result", result);
            model.addAttribute("fromCurrency", currencyService.getCurrencyByCode(request.getFromCurrency()));
            model.addAttribute("toCurrency", currencyService.getCurrencyByCode(request.getToCurrency()));

            List<Currency> currencies = currencyService.getSupportedCurrencies();
            model.addAttribute("currencies", currencies);
            model.addAttribute("conversionRequest", request);

            return "index";

        } catch (Exception e) {
            List<Currency> currencies = currencyService.getSupportedCurrencies();
            model.addAttribute("currencies", currencies);
            model.addAttribute("error", "Conversion failed: " + e.getMessage());
            return "index";
        }
    }
}
