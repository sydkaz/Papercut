package com.papercut.test;
import com.papercut.test.consts.PaperTypes;
import com.papercut.test.core.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.TreeMap;

public class ParseAndLoadData {
    TreeMap<String,Paper> papers;
    ArrayList<String> badData;

    public ParseAndLoadData() {
        this.papers=new TreeMap<String,Paper>();
        badData=new ArrayList<String>();
    }

    public ArrayList<String> getBadData() {
        return badData;
    }


    public TreeMap<String, Paper> loadData(String filePath, PaperTypes paperType){
        try {
            return loadAndProcess(filePath,paperType);
        } catch (IOException e) {
            System.out.println("File not found please make sure you supplied the correct path");
        }catch (NumberFormatException e) {
            System.out.println("Please provide the file with correct format i.e \"Number,Number,True/False\"");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /*This is for the case the company decides to pass the page type as job in the sample file like file1_incorrectData0_wring_column_names/sample.csv
    * */
    private boolean getValueByName(CSVRecord csvRecord,String key){
        try{
            csvRecord.get(key);
            return true;
        }
        catch (IllegalArgumentException e){
            return  false;
        }
    }

    private TreeMap<String,Paper> loadAndProcess(String filePath,PaperTypes defaultPaperType) throws Exception {
        Reader reader = Files.newBufferedReader(Paths.get(filePath));
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());
        for (CSVRecord csvRecord : csvParser) {
            if(! (csvParser.getHeaderMap().containsKey(csvRecord.get("Total Pages")) || csvParser.getHeaderMap().containsKey(csvRecord.get("Color Pages")) || csvParser.getHeaderMap().containsKey(csvRecord.get("Double Sided")))){ /*Skipping the first row if contains titles*/
                int totalPages = Integer.parseInt(csvRecord.get("Total Pages").trim());
                int  colourPages = Integer.parseInt(csvRecord.get("Color Pages").trim());
                int  blackPages = totalPages - colourPages;
                boolean isDouble = (Boolean.parseBoolean(csvRecord.get("Double Sided").trim()));
                PaperTypes paperType= getValueByName(csvRecord,"Paper Type")?PaperTypes.valueOf(csvRecord.get("Paper Type").trim()):defaultPaperType;
                if(blackPages>=0 && colourPages>=0){
                    Paper bws;
                    if (isDouble) {
                        if (blackPages > 0) {
                            bws = new BlackAndWhitePrint2Side(blackPages, paperType);
                            if (papers.get(bws.getKey()) != null)
                                papers.get(bws.getKey()).setCount(papers.get(bws.getKey()).getCount() + blackPages);
                            else
                                papers.put(bws.getKey(), bws);
                        }
                        if (colourPages > 0) {
                            bws = new ColourPrint2Side(colourPages, paperType);
                            if (papers.get(bws.getKey()) != null)
                                papers.get(bws.getKey()).setCount(papers.get(bws.getKey()).getCount() + colourPages);
                            else
                                papers.put(bws.getKey(), bws);
                        }
                    } else {
                        if (blackPages > 0) {
                            bws = new BlackAndWhitePrint1Side(blackPages, paperType);
                            if (papers.get(bws.getKey()) != null)
                                papers.get(bws.getKey()).setCount(papers.get(bws.getKey()).getCount() + blackPages);
                            else
                                papers.put(bws.getKey(), bws);
                        }
                        if (colourPages > 0) {
                            bws = new ColourPrint1Side(colourPages, paperType);
                            if (papers.get(bws.getKey()) != null)
                                papers.get(bws.getKey()).setCount(papers.get(bws.getKey()).getCount() + colourPages);
                            else
                                papers.put(bws.getKey(), bws);
                        }
                    }
                }else{
                    badData.add("Total: "+totalPages+" Page(s), Colour page(s): "+colourPages+" ,Black page(s): "+blackPages+" ,Double sides: "+isDouble);
                }
            }
        }
        return papers;
    }
}
