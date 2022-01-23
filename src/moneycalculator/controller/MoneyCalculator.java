package moneycalculator.controller;

import java.util.List;
import moneycalculator.model.Currency;
import moneycalculator.persistance.CurrencyLoader;
import moneycalculator.persistance.ExchangeRateLoader;
import moneycalculator.persistance.CurrencyLoaderArchive;
import moneycalculator.persistance.ExchangeRateLoaderWebService;


public class MoneyCalculator {

    public static void main(String[] args) {
        CurrencyLoader currencyLoader = new CurrencyLoaderArchive("Currencies.txt");
        ExchangeRateLoader exchangeRate = new ExchangeRateLoaderWebService("https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/latest/currencies/");
        
        List<Currency> listCurrencies = currencyLoader.loadAllCurrencies();
        
        MainFrame moneyCalculatorFrame = new MainFrame(listCurrencies, exchangeRate);
    }
    
}
