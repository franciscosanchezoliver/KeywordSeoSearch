package classes.scrapping;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

/**
 * This class contain a list of scrap results. For example a list of searches about the word "futbol" 
 */
public class Report {
    ArrayList<ScrapResult> scrapResults ;
    
    public Report () {
        scrapResults = new ArrayList<>();
    }

    public List<ScrapResult> getScrapResults( ) {
        return this.scrapResults;
    }    
    
    public ScrapResult getScrapResult(int index ) {
        return this.scrapResults.get(index);
    }
    
    public void addScrapResult(ScrapResult scrapResult) {
        this.scrapResults.add(scrapResult);
    }
    
    public String toJson() {
        return new Gson().toJson(scrapResults);
    }
    
}
