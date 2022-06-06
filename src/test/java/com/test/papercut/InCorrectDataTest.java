package com.test.papercut;

import com.papercut.test.consts.PaperTypes;
import com.papercut.test.core.A4PriceVisitor;
import com.papercut.test.ParseAndLoadData;
import com.papercut.test.core.Paper;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runners.MethodSorters;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.TreeMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class InCorrectDataTest {
    static TreeMap<String,Paper> page;
    static ParseAndLoadData pld;
    static A4PriceVisitor a4Price;
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @BeforeClass
    public static void beforeClass(){
        a4Price=new A4PriceVisitor();
        System.out.println("Instantiating array list in ParseAndLoadData ...");
    }

    @Test
    public void firstLoadPagesWithCorrectData() throws Throwable {
        pld =new ParseAndLoadData();
        Method method = com.papercut.test.ParseAndLoadData.class.getDeclaredMethod("loadAndProcess", String.class,PaperTypes.class);
        method.setAccessible(true);
        thrown.expect(NumberFormatException.class);
        try {
            method.invoke(pld, getFileAbsolutePath("file1_incorrectData0/sample.csv"),PaperTypes.A4);
        }
        catch (InvocationTargetException e){
           throw e.getTargetException();
        }
    }

    @Test
    public void secondLoadPagesWithCorrectData() throws Throwable {
        pld =new ParseAndLoadData();
        Method method = com.papercut.test.ParseAndLoadData.class.getDeclaredMethod("loadAndProcess", String.class,PaperTypes.class);
        method.setAccessible(true);
        thrown.expect(NumberFormatException.class);
        try {
            method.invoke(pld, getFileAbsolutePath("file1_incorrectData1/sample.csv"),PaperTypes.A4);
        }
        catch (InvocationTargetException e){/*For getting the actual exception not the reflect exceptions*/
            throw e.getTargetException();
        }
    }

    @Test
    public void thirdLoadPagesWithCorrectData() throws Throwable {
        pld =new ParseAndLoadData();
        Method method = com.papercut.test.ParseAndLoadData.class.getDeclaredMethod("loadAndProcess", String.class,PaperTypes.class);
        method.setAccessible(true);
        thrown.expect(IllegalArgumentException.class);
        try {
            method.invoke(pld, getFileAbsolutePath("file1_incorrectData0_wring_column_names/sample.csv"),PaperTypes.A4);
        }
        catch (InvocationTargetException e){/*For getting the actual exception not the reflect exceptions*/
            throw e.getTargetException();
        }

    }

    public String getFileAbsolutePath(String s){
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(s).getFile());
        System.out.println("Found Path for "+s+"   "+file.getAbsolutePath());
        return file.getAbsolutePath();
    }

}