package classes.scrapping;

import java.util.Date;

/**
 * Request of a keyword. For example if you want to see the results of the keyword "baloncesto".
 */
public class ScrapingRequest implements Comparable<ScrapingRequest> {

    private String keyword;
    private String url;
    private int period;
    private Date start; // When start the scrap
    private Date end; // Until when

    /**
     * This scraping request is going to last forever since this dont have a any end time
     */
    public ScrapingRequest(String keyword, int period) {
        this.keyword = keyword;
        this.period = period;
    }
    
    /**
     * This scraping request is going to execute during a determined amount of time since the time specified
     */
    public ScrapingRequest(String keyword, int period, Date start, Date end) {
        this.keyword = keyword;
        this.period = period;
        this.start = start;
        this.end = end;
    }

    /**
     *  This scraping request is going to start now until the end given
     */
    public ScrapingRequest(String keyword, int period, Date end) {
        this.keyword = keyword;
        this.period = period;
        this.end = end;
    }

    /**
     * Request of a URL appear if you search a certain keyword For example if the url "mundodeportivo.com" appear if you search for "futbol"
     */
    public ScrapingRequest(String url, String keyword, int period) {
        this.url = url;
        this.keyword = keyword;
        this.period = period;
    }    
    
    /**
     * We consider that a request is the same if the url or the keyword is the same
     */
    @Override
    public int compareTo(ScrapingRequest o) {
        // Compare for url or keyword
        if(o.getUrl() !=null && this.url !=null) {
            return o.getUrl().compareTo(this.url);
        }
        if(o.getKeyword() !=null && this.keyword !=null) {
            return o.getKeyword().compareTo(this.keyword);
        }        
        return -1;
    }
    
    /**
     * Check if the request is going to be requested foerever or if it has and end
     */
    public boolean hasEnded() {
        // If this request hasn't have a end then this is a permanent request 
        if(end == null)
            return false;
        Date now = new Date();
        if(end.after(now))
            return true;
        return false;
    }

    
    // **** GETTERS AND SETTERS ****
    
	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}
    

    
    
}








