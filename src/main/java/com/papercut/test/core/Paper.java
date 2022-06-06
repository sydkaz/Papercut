package com.papercut.test.core;

import com.papercut.test.consts.PaperSides;
import com.papercut.test.consts.PaperTypes;
import com.papercut.test.consts.PrintTypes;

public abstract class Paper {
    int count;
    PaperSides paperSides;
    PaperTypes paperTypes;
    PrintTypes printType;

    protected Paper(int count) {
        this.count = count;
    }

    protected Paper(int count, PaperTypes paperTypes, PrintTypes printType, PaperSides paperSides) {
        this.count = count;
        this.paperSides = paperSides;
        this.paperTypes = paperTypes;
        this.printType = printType;
    }

    protected abstract double accept(Visitor visitor);

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getKey() {
        return paperTypes + "_" + printType + "_" + paperSides;
    }
}
