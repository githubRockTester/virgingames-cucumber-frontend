package com.virgingames.browserfactory;

import com.virgingames.propertyreader.PropertyReader;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class ManageBrowser {
    //Declared WebDriver interface globally
    public static WebDriver driver;

    //Calling the base URL via Property Reader class and getProperty method
    static String baseUrl = PropertyReader.getInstance().getProperty("baseUrl");

    //Declaring constructor
    public ManageBrowser() {
        PageFactory.initElements(driver, this);
        PropertyConfigurator.configure(System.getProperty("user.dir") + "/src/test/java/resources/propertiesfile/log4j2.properties");
    }

    //Declared different browsers
    public void selectBrowser(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        } else {
            System.out.println("Wrong browser name");
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(
                Long.parseLong(PropertyReader.getInstance().getProperty("implicitlyWait"))));
        driver.get(baseUrl);
        driver.findElement(By.xpath("//label[@data-qa = 'accept-cookie-policy']")).click();
    }

    //Closing browser
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}
