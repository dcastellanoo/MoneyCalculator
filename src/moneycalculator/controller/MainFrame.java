package moneycalculator.controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import moneycalculator.model.Currency;
import moneycalculator.model.Money;
import moneycalculator.persistance.ExchangeRateLoader;
import moneycalculator.view.SwingDialog;
import moneycalculator.view.SwingDisplay;

public class MainFrame extends JFrame {
    private final SwingDialog swingDialog;
    private SwingDisplay swingDisplay;
    private ExchangeRateLoader exchangeRate;
    private JLabel tittle;
    
    public MainFrame(List<Currency> currencies, ExchangeRateLoader exchangeRate) {
        this.exchangeRate = exchangeRate;
        tittle = new JLabel("   MONEY CALCULATOR");
        tittle.setFont(new java.awt.Font("Trebuchet MS", 1, 18));
        tittle.setOpaque(true);
        tittle.setBackground(Color.yellow);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel pane = new JPanel();
        pane.setLayout(new BorderLayout());
        swingDialog = new SwingDialog(currencies, this);
        swingDisplay = new SwingDisplay();
        pane.add(tittle, BorderLayout.NORTH);
        pane.add(swingDialog, BorderLayout.CENTER);
        pane.add(swingDisplay, BorderLayout.SOUTH);
        this.getContentPane().add(pane);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        pack();
        this.setVisible(true);
    }
    
    public void updateDisplay(){
        try {
            swingDisplay.display(calculateAmount(swingDialog.getMoney(), swingDialog.getCurrencyTo()));
        }
        catch (Exception e){
             swingDisplay.defaultDisplay();
        }
    }
    
    private Money calculateAmount(Money money, Currency currencyTo){
        double newAmount = money.getAmount() * exchangeRate.get(money.getCurrency(),currencyTo).getRate();
        return new Money(newAmount, currencyTo);
    }
}
