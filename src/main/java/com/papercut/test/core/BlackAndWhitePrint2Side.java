package com.papercut.test.core;

import com.papercut.test.consts.PaperSides;
import com.papercut.test.consts.PaperTypes;
import com.papercut.test.consts.PrintTypes;

public class BlackAndWhitePrint2Side extends Paper {


    public BlackAndWhitePrint2Side(int count, PaperTypes paperTypes) {
        super(count, paperTypes, PrintTypes.BLACK, PaperSides.DOUBLE);
    }

    public double accept(Visitor visitor) {
        return visitor.visit(this);
    }

}
