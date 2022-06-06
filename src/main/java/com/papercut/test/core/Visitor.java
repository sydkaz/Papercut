package com.papercut.test.core;

/**
 * This will be used by the papertype class and overrride the abstract methods and when executed on method it will bind this object and when the function is executed it will execute the
 * method from which class we are calling from through overloading
*/

public interface Visitor {
    public double visit(BlackAndWhitePrint1Side printItem);
    public double visit(BlackAndWhitePrint2Side printItem);
    public double visit(ColourPrint1Side printItem);
    public double visit(ColourPrint2Side printItem);
}