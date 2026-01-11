package com.vts.prodavnicaobuce.selenium;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginTest {

    @Test
    void testLoginEdge() {
        System.setProperty("webdriver.edge.driver", "C:\\drivers\\msedgedriver.exe");
        WebDriver driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost:4200/login");

        try {
            driver.findElement(By.name("username")).sendKeys("Rasa");
            driver.findElement(By.name("password")).sendKeys("RasaPass*87");
            driver.findElement(By.className("btn-login")).click();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.urlContains("/pocetna"));

            String currentUrl = driver.getCurrentUrl();
            if (currentUrl.contains("/pocetna")) {
                System.out.println("Login test prošao!");
            } else {
                System.out.println("Login test nije prošao!");
            }

            Thread.sleep(5000);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           driver.quit();
        }
    }
}
