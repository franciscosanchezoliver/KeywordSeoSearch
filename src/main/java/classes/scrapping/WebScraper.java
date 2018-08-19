package classes.scrapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.google.gson.Gson;

import classes.utils.Driver;


/**
 * This class is responsible of getting the information from Google and parse this information to the proper class.
 */
public class WebScraper {

    private WebDriver driver = null;
    private String CHROME_DRIVER_PATH = "src\\main\\resources\\webdrivers\\chromedriver.exe";
    private String FIREFOX_DRIVER_PATH = "src\\main\\resources\\webdrivers\\geckodriver.exe";
    private String GOOGLE_URL = "http://www.google.com";
    private String NOTICIAS_DESTACADAS_XPATH = "//g-card-section/a";
    private String CAROUSEL_CLASS = "//a[@class='amp_r']";
    private Boolean running = true;// Control if the WebScrapper is running

    private ScrapingRequest scrapRequest = null;

    public WebScraper() {
        this.driver = getDriver();
        this.running = false;
    }

    /**
     * Check if the WebScrapper is running
     */
    public boolean isBusy() {
        return running;
    }

    public void setRunning(boolean condition) {
        synchronized (running) {
            this.running = condition;
        }
    }

    /**
     * Search the given string in Google and track the result
     */
    public ScrapResult scrapTheWeb(ScrapingRequest scrapRequest) {
        if (!running) {
            setRunning(true);
            this.scrapRequest = scrapRequest;
            ScrapResult webPage = new ScrapResult();
            searchInGoogle(); // Search the given string in google
            webPage.setCarousel(new Carousel(getNewsInCarousel())); // Get carousel
            webPage.setPopularNews(new PopularNews(getPopularNews())); // Get popular news
            setRunning(false);
            return webPage;
        } else {
            System.err.println("WebScrapper can't scrap several pages at the same time...");
            return null;
        }
    }

    /**
     * Cuando hacemos una busqueda en google en AMP primero saldrá 1 o más noticias (normalmente no he visto mas de 3 en noticias
     * destacadas). Estas noticias serán las mas relevantes y se obtienen de forma distinta al resto de noticias
     * 
     * @return
     */
    public List<WebElement> getPopularNews() {
        return driver.findElements(By.xpath(NOTICIAS_DESTACADAS_XPATH));
    }

    /**
     * Get the element of the carousel that can be scrolled
     */
    public List<WebElement> getNewsInCarousel() {
        return driver.findElements(By.xpath(CAROUSEL_CLASS));
    }

    /**
     * Search the given keyword in google
     */
    private void searchInGoogle() {
        driver.get(GOOGLE_URL);
        try {
            WebElement element = driver.findElement(By.name("q"));// Select Google's search bar
            element.sendKeys(scrapRequest.getKeyword()); // Write down the keyword
            element.submit(); // Submit to search
        } catch (NoSuchElementException e) {
            System.err.println("Keyword not found in google");
        }
    }

    /**
     * Get the driver that does the actual search in Google. This driver can be Mozilla, Chrome, etc...
     */
    private WebDriver getDriver() {
	   	return Driver.getChromeDriver();
//	   	return Driver.getMozillaDriver();
    }

}
