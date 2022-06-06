package com.papercut.test.core;

import com.papercut.test.consts.PaperSides;
import com.papercut.test.consts.PaperTypes;
import com.papercut.test.consts.PrintTypes;

public class BlackAndWhitePrint1Side extends Paper {
    public BlackAndWhitePrint1Side(int count,PaperTypes paperTypes) {
        super(count, paperTypes, PrintTypes.BLACK, PaperSides.SINGLE);
    }

    @Override
    public double accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
