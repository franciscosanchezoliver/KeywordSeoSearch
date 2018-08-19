package classes.scrapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.google.gson.Gson;

/**
 * This class represents the differents elements that google return when you search something
 *
 */
public class ScrapResult {
    LocalDateTime date = null; // The date when the page was searched
    Carousel carousel = null; // google's carousel
    PopularNews popularNews = null; // google's popular news
    
    public ScrapResult() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        date = LocalDateTime.now();
    }
    
    public void setCarousel(Carousel carousel) {
        this.carousel = carousel;
    }
    
    public Carousel getCarousel() {
        return this.carousel;
    }
    
    public void setPopularNews(PopularNews popularNews) {
        this.popularNews = popularNews;
    }    
    
    public LocalDateTime getDate() {
        return this.date; 
    }
    
    public String getJson() {
        return new Gson().toJson(this);
    }

}
