package moneycalculator.persistance;

import java.util.List;
import moneycalculator.model.Currency;
import moneycalculator.model.ExchangeRate;

public interface ExchangeRateLoader {
    public  ExchangeRate get(Currency from, Currency to);
}
