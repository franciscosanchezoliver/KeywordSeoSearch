package classes.utils;



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import classes.scrapping.Report;
import classes.scrapping.ScrapResult;


public class FileManager {

    private final String DIRECTORY_PATH = "src\\main\\resources\\keywords\\";
    private String filePath = null;

    public FileManager(String termSearched) {
        this.filePath = getFilePath(termSearched); // The path is calculated according to the string given
    }

    public void save(ScrapResult content) {
        // If the file exists then append the new content to the existing file
        if (exist()) {
            appendToFile(content);
        }
        // If the file doesnt exist then write the content
        else {
            writeFile(content);
        }
    }

    public boolean exist() {
        File f = new File(filePath);
        if (f.exists() && !f.isDirectory()) {
            return true;
        }
        return false;
    }

    /**
     * Write in file the first scrap result
     */
    public void writeFile(ScrapResult scrapResult) {
        Report scrappingReport = new Report();
        scrappingReport.addScrapResult(scrapResult);
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(filePath));
            writer.write(JSONConverter.toJson(scrappingReport));
            writer.close();
        } catch (IOException e) {
            System.err.println("Error writing file in path:" + filePath);
            e.printStackTrace();
        }
    }

    public void appendToFile(ScrapResult newScrapResult) {
        String oldContent = readFile();
        Report report = JSONConverter.getReport(oldContent);
        report.addScrapResult(newScrapResult);
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(filePath));
            writer.write(JSONConverter.toJson(report));
            writer.close();
        } catch (IOException e) {
            System.err.println("Error appending in following file: " + filePath);
            e.printStackTrace();
        }
    }

    public String readFile() {
        String fileContent = "";
        File file = new File(filePath);
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(file));
            String st;
            while ((st = br.readLine()) != null) {
                fileContent += st;
            }
        } catch (FileNotFoundException e) {
            System.err.println("The following file doesnt exists: " + filePath);
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Error reading the following file: " + filePath);
            e.printStackTrace();
        }
        return fileContent;
    }

    /**
     * Return a name for a file given a string
     * 
     * @param name
     * @return
     */
    private String getFilePath(String name) {
        String result = "";
        if (isURL(name)) name = name.split("/")[name.split("/").length - 1].split("\\?")[0];
        return DIRECTORY_PATH + name + ".json";
    }

    private boolean isURL(String name) {
        if (name.contains("//")) return true;
        return false;
    }

}
