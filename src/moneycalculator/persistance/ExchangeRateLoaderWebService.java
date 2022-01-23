package moneycalculator.persistance;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import moneycalculator.model.Currency;
import moneycalculator.model.ExchangeRate;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import moneycalculator.persistance.ExchangeRateLoader;

public class ExchangeRateLoaderWebService implements ExchangeRateLoader{
    private final String base_url;

    public ExchangeRateLoaderWebService(String url) {
        this.base_url = url;
    }
    
    @Override
    public ExchangeRate get(Currency from, Currency to) {
        try {
            URL url = new URL(this.base_url + from.getCode() + "/" + to.getCode() + ".json");
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String jsonArray = "";
            while (true) {
                String inputLine = reader.readLine();
                if (inputLine == null) break;
                jsonArray += inputLine;
            }
            return ExchangeRateOf(from, to, jsonArray);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ExchangeRateLoaderWebService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ExchangeRateLoaderWebService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (org.json.simple.parser.ParseException ex) {
            Logger.getLogger(ExchangeRateLoaderWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private static ExchangeRate ExchangeRateOf(Currency from, Currency to, String jsonArray) throws org.json.simple.parser.ParseException{
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject =  (JSONObject) jsonParser.parse(jsonArray);
        double rate = Double.parseDouble(jsonObject.get(to.getCode()).toString());
        return new ExchangeRate(rate, from, to);
    }
    
}
