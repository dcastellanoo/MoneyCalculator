package moneycalculator.persistance;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import moneycalculator.model.Currency;
import moneycalculator.persistance.CurrencyLoader;

public class CurrencyLoaderArchive implements CurrencyLoader{
    private final String filePathName;

    public CurrencyLoaderArchive(String filePathName) {
        this.filePathName = filePathName;
    }
    
    private Currency currencyOf(String lineText) {
        String[] splitedLine = lineText.split(",");
        return new Currency(splitedLine[0],splitedLine[1],splitedLine[2]);
    }
    
    @Override
    public List<Currency> loadAllCurrencies() {
        List<Currency> listCurrencies = new ArrayList<>();
        
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(this.filePathName)));
            while (true) {
                String lineText = reader.readLine();
                if(lineText == null) break;
                listCurrencies.add(currencyOf(lineText));
            }
        }
        catch (FileNotFoundException exception) {
            System.out.println("ERROR CurrencyLoaderArchive::loadAllCurrencies FileNotFoundException, " + exception.getLocalizedMessage());
        } catch (IOException ex) {
            System.out.println("ERROR CurrencyLoaderArchive::loadAllCurrencies IOException, " + ex.getLocalizedMessage());
        }
        
        return listCurrencies;
    }
    
}
