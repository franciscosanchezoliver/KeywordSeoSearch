package classes.scrapping;

import java.util.LinkedList;

import classes.controllers.KeywordController;
import classes.utils.FileManager;


/**
 * This class represents a manager that decides whose request is attended
 */
public class RequestScheduler implements Runnable {

    LinkedList<ScrapingRequest> requests = new LinkedList<ScrapingRequest>();// Stack of requests
    WebScraper webScraper = new WebScraper();// Object that does the petition and scrap the web
    private volatile boolean running = true;// Control if the scheduler is running
    private int period = 0; // Time between execution in minutes
    private int DEFAULT_PERIOD = 15; // Time between execution in minutes (By default 15 min)
    private FileManager fileManager = null; // Do all the operations with the files (write and read)

    @Override
    public void run() {
        while (running) {
            try {
                // If there are request unattended
                if (thereAreMoreRequests() && !webScraper.isBusy()) {
                    ScrapingRequest nextRequest = getNext(); // Get the next request
                    System.out.println("Scrapping \"" + nextRequest.getKeyword() + "\"");
                    ScrapResult result = webScraper.scrapTheWeb(nextRequest);// Scraps the web
                    fileManager = new FileManager(nextRequest.getKeyword());
                    fileManager.save(result); // Save the result
                    KeywordController.server.addUpRequestsDone();
                    // If the request have to be repeated again then we add it to the list
                    if (!nextRequest.hasEnded()) requests.add(nextRequest);
                    waitToNextSearch(); // Wait a certain time to attend the next request
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                running = false;
            }
        }
    }

    /**
     * Wait X minutes until the next search. DEFAULT_PERIOD min if no time is specified.
     * 
     * @throws InterruptedException
     */
    private void waitToNextSearch() throws InterruptedException {
        if (period != 0) {
            Thread.sleep(toMiliseconds(period));
        } else {
            Thread.sleep(toMiliseconds(DEFAULT_PERIOD));
        }
    }

    /**
     * Transform minutes to miliseconds.
     */
    private int toMiliseconds(int minutes) {
        return minutes * 1000;
    }

    /**
     * Add a new request to the stack
     */
    public void addRequest(ScrapingRequest scrapingRequest) {
        requests.addLast(scrapingRequest);
    }

    /**
     * Delete one request
     */
    public void delete(String key) {
        int index = getIndexOfRequest(key);
        if(requests.remove(index)!= null) {
            System.out.println("Tracker on " +  key +  " stopped");
        }
    }

    /**
     * Get the next request to attend
     */
    public ScrapingRequest getNext() {
        return requests.removeFirst();
    }

    /**
     * Get the index of a request
     */
    private int getIndexOfRequest(String key) {
        ScrapingRequest requestToDelete = new ScrapingRequest(key, -1);
        int index = 0;
        for (int i = 0; i < this.requests.size(); i++) {
            if (requests.get(i).compareTo(requestToDelete) == 0) {
                return index;
            }
            index++;
        }
        return index;
    }

    /**
     * Check if there are unattended requests
     */
    private boolean thereAreMoreRequests() {
        return requests.size() > 0;
    }

}
