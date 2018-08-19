package classes.services;

import classes.scrapping.RequestScheduler;
import classes.scrapping.ScrapingRequest;

import classes.utils.FileManager;

public class Service {

    private Thread webRequester = null; // This thread will be requesting to Google
    public static RequestScheduler requestScheduler = null; // Manage the turn of the next request

    public Service() {     
        requestScheduler = getRequestScheduler();
    }

    public RequestScheduler getRequestScheduler() {
        if (requestScheduler == null) {
            requestScheduler = new RequestScheduler();
            Thread thread = new Thread(requestScheduler);
            thread.start();
        }
        return this.requestScheduler;
    }

    public void stopSearch(String keyword) {
        requestScheduler.delete(keyword);
    }

    /**
     * Add a new request to the stack
     */
    public void startSearch(String keyword, int period) {
        requestScheduler.addRequest(new ScrapingRequest(keyword, period));
    }

    public String getKeywordInfo(String keyword) {
        FileManager fileManager = new FileManager(keyword);
        String fileContent = fileManager.readFile();
        return fileContent;
    }

}
