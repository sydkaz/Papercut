/**
 * Main entry point fot the app, It takes the path for the file that has job and optional additional parameter which could be (A1-A10) defining the paper size that will get the object for the prices
 * from the factory class.
 *
 * Optionally the paper type can be provided in the text file which has the highest precedence and can work with other page types as well.
 *
 *Precedence for the page type
 *higest: in the data file please refer to "file1_correctData_with_additional_column" file
 *low: passed on command line argument followed by the path of the csv file.
 *
 *
 *
 */
package com.papercut.test;

import com.papercut.test.consts.PaperTypes;
import com.papercut.test.core.Display;
import com.papercut.test.core.Paper;

import java.util.ArrayList;
import java.util.TreeMap;

public class Run {
    public static void main(String[] args){
        if(args.length  ==2)
        {
            String fileName = args[0];
            PaperTypes paperTypes=PaperTypes.valueOf(args[1]);
            TreeMap<String,Paper> papers;
            ArrayList<String> badData;
            ParseAndLoadData pld=new ParseAndLoadData();
            papers=pld.loadData(fileName,paperTypes);
            badData=pld.getBadData();
            Display.printJobPrices(papers,badData);
        }
        else
        {
            String fileName = args[0];
            PaperTypes paperTypes=PaperTypes.A4;
            TreeMap<String,Paper> papers;
            ArrayList<String> badData;
            ParseAndLoadData pld=new ParseAndLoadData();
            papers=pld.loadData(fileName,paperTypes);
            badData=pld.getBadData();
            Display.printJobPrices(papers,badData);
        }
    }
}
