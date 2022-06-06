/**
 * Used for Factory and key generation for the object
 */
package com.papercut.test.consts;

import com.papercut.test.core.Visitor;
import com.papercut.test.core.A4PriceVisitor;
import com.papercut.test.core.A5PriceVisitor;
import java.util.HashMap;

public enum PaperTypes {
    A1("A1"),
    A2("A2"),
    A3("A3"),
    A4("A4"),
    A5("A5"),
    A6("A6"),
    A7("A7"),
    A8("A8"),
    A9("A9"),
    A10("A10");
    private static HashMap<String,Visitor> paperTypesList=new HashMap<String, Visitor>();
    private String text;

    PaperTypes(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public static PaperTypes fromString(String text) {
        for (PaperTypes pt : PaperTypes.values()) {
            if (pt.text.equalsIgnoreCase(text)) {
                return pt;
            }
        }
        return null;
    }
    /*Object factory for calculation object if passed on runtime*/
    public static Visitor getPriceByName(String name){
        Visitor visitor=null;

        visitor = paperTypesList.get(name);
        if(visitor == null){
            switch(name) {
                case "A4" :
                    visitor= new A4PriceVisitor();
                    break;
                case "A5" :
                    visitor= new A5PriceVisitor();
                    break;
            }
            paperTypesList.put(name,visitor);
        }

        return visitor;
    }

}

