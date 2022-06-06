package com.papercut.test.core;

import com.papercut.test.consts.PaperTypes;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Display {
    static DecimalFormat df = new DecimalFormat("#.##");
    public static double printJobPrices(TreeMap<String, Paper> papers, ArrayList<String> badData){
        if (papers==null)
        return 0d;

        else{
            double totalCost=0;
            for(Map.Entry<String,Paper> entry : papers.entrySet()) {
                String key = entry.getKey();
                Visitor priceFactor=PaperTypes.getPriceByName(key.split("_")[0]);
                Paper p = entry.getValue();
                System.out.printf( "%s total pages => %d , total cost => %s \n" ,key, p.getCount(),df.format(p.accept(priceFactor)));
                totalCost+=p.accept(priceFactor);
            }
            System.out.printf("\nTotal cost for the job is %s",df.format(totalCost));

            if(null != badData && badData.size()>0)
            {
                System.out.printf("\n%d Numbers of record(s) has data issue \n",badData.size());
                for (String data:badData) {
                    System.out.printf("%s \n",data);
                }
            }

            return totalCost;
        }

    }
}
