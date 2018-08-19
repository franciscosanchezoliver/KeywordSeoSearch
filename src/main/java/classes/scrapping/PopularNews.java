package classes.scrapping;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.WebElement;
import com.google.gson.Gson;

public class PopularNews {

    ArrayList<PieceOfNew> news = new ArrayList<>(); // Save the cards that the carousel has
    
    public PopularNews() {}
    
    public PopularNews(List<WebElement> popularNews) {
        popularNews.forEach(x -> news.add(new PieceOfNew(x))); // Add all the popular news in the news list
    }
    
    /**
     * This class represents each element in the popular news
     */
    public static class PieceOfNew{
        private String href ;
        private String text;
        private String company; 
        private int hours;
        private int minutes;
        private int days;
        
        public PieceOfNew(WebElement webElement) {
            this.href = webElement.getAttribute("href");
            this.text = webElement.getText().split("\n")[0];
            this.company = setCompany(webElement);
            setTime(webElement);
        }
        
        /**
         * Set the name of the company 
         */
        private String setCompany(WebElement webElement) {
            String innerText  = webElement.getText();
            String secondPart =  innerText.split("\n")[1];
            return secondPart.split("-")[0].trim();
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
        
    }
}
