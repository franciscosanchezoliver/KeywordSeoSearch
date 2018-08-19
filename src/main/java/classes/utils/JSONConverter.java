package classes.utils;

import com.google.gson.Gson;

//import classes.scraping.Report;
//import classes.scraping.ScrapResult;
import classes.scrapping.Report;
import classes.scrapping.ScrapResult;

public class JSONConverter {
    
    private static Gson gson = new Gson();
    
    /**
     *  Set up a ScrapResult Object from a Json string
     */
    public static ScrapResult getScrapResult(String json) {
        return gson.fromJson(json, ScrapResult.class);
    }
    
    /**
     *  Set up a Report Object from a Json string
     */
    public static Report getReport(String json) {
        return gson.fromJson(json, Report.class);
    }
    
    /**
     * Get a Json string from an object Report 
     */
    public static String toJson(Report report) {
        return gson.toJson(report);
    }
    
}
