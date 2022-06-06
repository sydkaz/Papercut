/**
 * Price calculation class that will run the appropiate method that was binded in the Paper implementation class (Visitable class implementation)
 */
package com.papercut.test.core;

import java.text.DecimalFormat;

public class A4PriceVisitor implements Visitor {
    DecimalFormat df = new DecimalFormat("#.##");

    @Override
    public double visit(BlackAndWhitePrint1Side printItem) {
        return Double.parseDouble(df.format(0.15*printItem.getCount()));
    }
    @Override
    public double visit(BlackAndWhitePrint2Side printItem) {
        return Double.parseDouble(df.format(0.10* printItem.getCount()));
    }
    @Override
    public double visit(ColourPrint1Side printItem) {
        return Double.parseDouble(df.format(0.25 * printItem.getCount()));
    }
    @Override
    public double visit(ColourPrint2Side printItem) {
        return Double.parseDouble(df.format(0.20* printItem.getCount()));
    }
}