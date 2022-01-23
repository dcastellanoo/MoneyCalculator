package moneycalculator.view;

import moneycalculator.model.Money;

public interface Display {
    public void display(Money money);
    public void defaultDisplay();
}
