package moneycalculator.view;

import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import moneycalculator.model.Money;

public class SwingDisplay extends JPanel implements Display{
    private JTextField amountCalculated;
    
    public SwingDisplay(){
        this.setLayout(new FlowLayout());
        amountCalculated = new JTextField(15);
        amountCalculated.setHorizontalAlignment(JTextField.CENTER);
        this.add(amountCalculated);
    }

    @Override
    public void display(Money money) {
        amountCalculated.setForeground(Color.BLACK);
        amountCalculated.setText(""+money.getAmount()+" "+money.getCurrency().getSymbol());
    }

    @Override
    public void defaultDisplay() {
        amountCalculated.setForeground(Color.GRAY);
        amountCalculated.setText("Amount no valid");
    }
}
