package com.papercut.test.core;

import com.papercut.test.consts.PaperSides;
import com.papercut.test.consts.PaperTypes;
import com.papercut.test.consts.PrintTypes;

public class ColourPrint1Side extends Paper {
    public ColourPrint1Side(int count, PaperTypes paperTypes) {
        super(count, paperTypes, PrintTypes.COLOUR, PaperSides.SINGLE);
    }
    public double accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
