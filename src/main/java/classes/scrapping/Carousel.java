package classes.scrapping;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import org.openqa.selenium.WebElement;
import com.google.gson.Gson;

/**
 * This class represents the carousel that appears after searching in google using AMP.
 */
public class Carousel {

    ArrayList<Card> cards = new ArrayList<>(); // Save the cards that the carousel has

    public Carousel() {}
    
    public Carousel(List<WebElement> carousel) {
        carousel.forEach(x -> cards.add(new Card(x))); // Add all the cards in the carousel
    }       

    /**
     * Load the carousel with a given list of elements 
     */
    public void load(List<WebElement> carousel) {
        carousel.forEach(x -> cards.add(new Card(x))); // Add all the cards in the carousel
    }
    
    /**
     * @return Card of the given url in the carousel. Returns null if the url has not been found.
     */    
    public Card getCardByURL(String url) {
        return cards.stream().filter(card -> card.getHref().equals(url)).findAny().orElse(null);
    }
    
    /**
     * @return Position of the given url in the carousel. Returns -1 if the url has not been found.
     */    
    public int getPositionOfCardInCarousel(String url) {
        return IntStream.range(0, cards.size()).filter(index -> cards.get(index).getHref().equals(url)).findFirst().orElse(-1);
    }    
    
    /**
     * @return One card by position.
     */
    public Card getCard(int i) {
        return this.cards.get(i);
    }
    
    /**
     * @return All the cards
     */
    public List<Card> getCards(){
        return this.cards;
    }
    
    /**
     * This class represents each element in the carousel 
     */
    public static class Card{
        private String href ;
        private String text;
        private int hours;
        private int minutes;
        private int days;
        
        public Card(WebElement webElement) {
            this.href = webElement.getAttribute("href");
            this.text = webElement.getText().split("\n")[0];
            setTime(webElement);
        }
        
        /**
         * Get the time from the Google's card 
         */
        private void setTime(WebElement webElement) {
            String innerText = webElement.getText();
            String[] parts = innerText.split("\n");
            String time = parts[1];
            
            Pattern pattern = Pattern.compile("Hace[\\s](?<Cantidad>\\d+)[\\s](?<Unidad>\\w+)");
            Matcher matcher = pattern.matcher(time);
            
            if(matcher.matches()) {
                String cantidad = matcher.group("Cantidad");
                String medida = matcher.group("Unidad");
                
                if(medida.contains("minuto")) {
                    this.minutes = Integer.parseInt(cantidad);
                }
                else if(medida.contains("hora")) {
                    this.hours = Integer.parseInt(cantidad);
                }
                else {
                    this.days = Integer.parseInt(cantidad);
                }
                
            }
            
        }
        
        public String getHref() {
        	return this.href;
        }
        
    }
    
    public String getJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
    

}


