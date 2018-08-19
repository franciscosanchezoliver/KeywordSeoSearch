package classes.utils;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.springframework.context.annotation.Bean;

public class Driver {

    private static String CHROME_DRIVER_PATH = "src\\main\\resources\\webdrivers\\chromedriver.exe";
    private static String FIREFOX_DRIVER_PATH = "src\\main\\resources\\webdrivers\\geckodriver.exe";


    @Bean
    public static WebDriver getChromeDriver() {
    	WebDriver driver = null;
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
        HashMap<String, String> mobileEmulation = new HashMap<>();
        // Info about supported mobile emulations: http://chromedriver.chromium.org/mobile-emulation
        mobileEmulation.put("deviceName", "Nexus 6P");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
        // To execute selenium without a graphical interface
        chromeOptions.addArguments("headless");
        return new ChromeDriver(chromeOptions);
    }

//    @Bean
//    public static WebDriver getMozillaDriver() {
//        FirefoxBinary firefoxBinary = new FirefoxBinary();
//        // Run firefox without an interface
//        firefoxBinary.addCommandLineOptions("--headless");
//        // Use geckodriver as webdriver
//        System.setProperty("webdriver.gecko.driver", FIREFOX_DRIVER_PATH);
//        FirefoxOptions firefoxOptions = new FirefoxOptions();
//        firefoxOptions.setBinary(firefoxBinary);
//        
//        // Set Mobile
//        String user_agent = "Mozilla/5.0 (Linux; Android 6.0.1; Nexus 6P Build/MHC19Q) AppleWebKit/537.36 (KHTML, like Gecko) 46.0.2490.0 Mobile Safari/537.36";
//        FirefoxProfile profile = new FirefoxProfile();
//        profile.setPreference("general.useragent.override", user_agent);
//        firefoxOptions.setProfile(profile);
//        
//        return  new FirefoxDriver(firefoxOptions);
//    }
    
}
