package moneycalculator.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import moneycalculator.controller.MainFrame;
import moneycalculator.model.Currency;
import moneycalculator.model.Money;

public class SwingDialog extends JPanel implements Dialog{
    private final JLabel labelAmount;
    private final JLabel labelFrom;
    private final JLabel labelTo;
    private JTextField textFieldAmountFrom;
    private final JComboBox<String> comboBoxCurrencyFrom;
    private final JComboBox<String> comboBoxCurrencyTo;
    private MainFrame controller;
    
    public SwingDialog(List<Currency> listCurrencies, MainFrame controller) {
        this.controller = controller;
        labelAmount = new JLabel("Amount:  ");
        textFieldAmountFrom = new JTextField(12);
        textFieldAmountFrom.setHorizontalAlignment(JTextField.CENTER);
        textFieldAmountFrom.addKeyListener(new textFieldAmountListener());
        labelFrom = new JLabel("From: ");
        labelTo = new JLabel("To:      ");
        comboBoxCurrencyFrom = jComboBoxCurrency(listCurrencies);
        comboBoxCurrencyFrom.addActionListener(new comboBoxCurrencyFromListener());
        comboBoxCurrencyTo = jComboBoxCurrency(listCurrencies);
        comboBoxCurrencyTo.addActionListener(new comboBoxCurrencyToListener());
        
        JPanel north = new JPanel();
        north.setLayout(new FlowLayout(FlowLayout.LEADING));
        north.add(labelAmount);
        north.add(textFieldAmountFrom);
        
        JPanel center = new JPanel();
        center.setLayout(new FlowLayout(FlowLayout.LEADING));
        center.add(labelFrom);
        center.add(comboBoxCurrencyFrom);
        
        JPanel south = new JPanel();
        south.setLayout(new FlowLayout(FlowLayout.LEADING));
        south.add(labelTo);
        south.add(comboBoxCurrencyTo);
        
        this.setLayout(new BorderLayout());
        this.add(north, BorderLayout.NORTH);
        this.add(center, BorderLayout.CENTER);
        this.add(south, BorderLayout.SOUTH);
    }
    
    private JComboBox jComboBoxCurrency(List<Currency> listCurrencies){
        JComboBox jComboBoxCurrency = new JComboBox();
        for (Currency c : listCurrencies) {
            jComboBoxCurrency.addItem(c);
        }
        return jComboBoxCurrency;
    }
    
    
    
    @Override
    public Money getMoney() {
        try {
            double amount = Double.parseDouble(this.textFieldAmountFrom.getText());
            if (amount < 0.){
                return null;
            } 
            return new Money(amount, this.getCurrencyFrom());
        }catch(Exception e){
            return null;
        }
        
    }
    
    @Override
    public Currency getCurrencyFrom() {
        Currency currencyFrom = (Currency)this.comboBoxCurrencyFrom.getSelectedItem();
        return currencyFrom;
    }

    @Override
    public Currency getCurrencyTo() {
        Currency currencyTo = (Currency)this.comboBoxCurrencyTo.getSelectedItem();
        return currencyTo;
    }

    private class textFieldAmountListener implements KeyListener{

        @Override
        public void keyTyped(KeyEvent ke) {
        }

        @Override
        public void keyPressed(KeyEvent ke) {
         
        }

        @Override
        public void keyReleased(KeyEvent ke) {
            controller.updateDisplay();
        }
        
    }
    
    private class comboBoxCurrencyFromListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            controller.updateDisplay();
        }
        
    }
    
    private class comboBoxCurrencyToListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            controller.updateDisplay();
        }
        
    }

    
}
