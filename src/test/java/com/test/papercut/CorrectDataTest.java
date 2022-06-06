package com.test.papercut;

import com.papercut.test.consts.PaperTypes;
import com.papercut.test.core.A4PriceVisitor;
import com.papercut.test.core.Display;
import com.papercut.test.ParseAndLoadData;
import com.papercut.test.core.Paper;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import java.io.File;
import java.util.TreeMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CorrectDataTest {
    static TreeMap<String,Paper> page;
    static ParseAndLoadData pld;
    static A4PriceVisitor a4Price;
    @BeforeClass
    public static void beforeClass(){
        a4Price=new A4PriceVisitor();
        System.out.println("Instantiating array list in ParseAndLoadData ...");
    }

    @Test
    public void firstLoadPagesWithCorrectData() throws Exception {
        pld =new ParseAndLoadData();
        page=pld.loadData(getFileAbsolutePath("file1_correctData0/sample.csv"),PaperTypes.A4);
        Assert.assertEquals(64.10, Display.printJobPrices(page, null),0);
    }

    @Test
    public void secondLoadPagesWithCorrectData() throws Exception {
        pld =new ParseAndLoadData();
        page=pld.loadData(getFileAbsolutePath("file1_correctData1/sample.csv"),PaperTypes.A4);
        Assert.assertEquals(59.35, Display.printJobPrices(page, null),0);
    }

    @Test
    public void thirdLoadPagesWithCorrectData() throws Exception {
        pld =new ParseAndLoadData();
        page=pld.loadData(getFileAbsolutePath("file1_correctData2/sample.csv"),PaperTypes.A4);
        Assert.assertEquals(64.10, Display.printJobPrices(page, null),0);
    }

    @Test
    public void fourthLoadPagesWithCorrectData() throws Exception {
        pld =new ParseAndLoadData();
        page=pld.loadData(getFileAbsolutePath("file1_correctData3/sample.csv"),PaperTypes.A4);
        Assert.assertEquals(64.10, Display.printJobPrices(page, null),0);
    }
    @Test
    public void fifthLoadPagesWithCorrectData() throws Exception {
        pld =new ParseAndLoadData();
        page=pld.loadData(getFileAbsolutePath("file1_correctData4/sample.csv"),PaperTypes.A4);
        Assert.assertEquals(11.55, Display.printJobPrices(page, null),0);
    }

    public String getFileAbsolutePath(String s){
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(s).getFile());
        System.out.println("Found Path for "+s+"   "+file.getAbsolutePath());
        return file.getAbsolutePath();
    }

}