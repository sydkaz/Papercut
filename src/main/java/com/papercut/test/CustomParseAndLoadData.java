/*
 * This Class is not bing used in the project and has only been added for the demonstration purpose, this was added int the orignal version of this challenge but chnage it to 3rd party library
 *
 * */
package com.papercut.test;

import com.papercut.test.consts.PaperTypes;
import com.papercut.test.core.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.TreeMap;
import java.util.stream.Stream;

public class CustomParseAndLoadData {
    TreeMap<String,Paper> papers;

    public CustomParseAndLoadData() {
        this.papers=new TreeMap<String,Paper>();
    }

    public TreeMap<String, Paper> loadData(String filePath){
        try {
            return loadAndProcess(filePath);
        } catch (IOException e) {
            System.out.println("File not found please make sure you supplied the correct path");
        }catch (NumberFormatException e) {
            System.out.println("Please provide the file with correct format i.e \"Number,Number,True/False\"");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    private TreeMap<String,Paper> loadAndProcess(String filePath) throws Exception {
        Stream<String> stream = Files.lines(Paths.get(filePath));
        stream.map(String::toUpperCase).filter(line -> !(line.startsWith("TOTAL") || line.startsWith("COLOR") || line.startsWith("TOTAL"))).map(currentLine -> currentLine.trim().split("\\s*,\\s*")).filter(temp -> temp.length == 3 && (temp[2].equalsIgnoreCase("true") || temp[2].equalsIgnoreCase("false"))).forEach(temp -> {
            int totalPages = Integer.parseInt(temp[0]);
            int colourPages = Integer.parseInt(temp[1]);
            int blackPages = totalPages - Integer.parseInt(temp[1]);
            boolean isDouble = (Boolean.parseBoolean(temp[2]));
            if (blackPages >= 0 && colourPages >= 0) {
                Paper bws;
                if (isDouble) {
                    if (blackPages > 0) {
                        bws = new BlackAndWhitePrint2Side(blackPages, PaperTypes.A4);
                        if (papers.get(bws.getKey()) != null)
                            papers.get(bws.getKey()).setCount(papers.get(bws.getKey()).getCount() + blackPages);
                        else
                            papers.put(bws.getKey(), bws);
                    }
                    if (colourPages > 0) {
                        bws = new ColourPrint2Side(colourPages, PaperTypes.A4);
                        if (papers.get(bws.getKey()) != null) {
                            papers.get(bws.getKey()).setCount(papers.get(bws.getKey()).getCount() + colourPages);
                        } else
                            papers.put(bws.getKey(), bws);
                    }
                } else {
                    if (blackPages > 0) {
                        bws = new BlackAndWhitePrint1Side(blackPages, PaperTypes.A4);
                        if (papers.get(bws.getKey()) != null)
                            papers.get(bws.getKey()).setCount(papers.get(bws.getKey()).getCount() + blackPages);
                        else
                            papers.put(bws.getKey(), bws);
                    }
                    if (colourPages > 0) {
                        bws = new ColourPrint1Side(colourPages, PaperTypes.A4);
                        if (papers.get(bws.getKey()) != null)
                            papers.get(bws.getKey()).setCount(papers.get(bws.getKey()).getCount() + colourPages);
                        else
                            papers.put(bws.getKey(), bws);
                    }
                }
            }
        });
        return papers;

    }
}