package com.vts.prodavnicaobuce.selenium;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class LogoutTest {

    @Test
    void testLogout() {
        System.setProperty("webdriver.edge.driver", "C:\\drivers\\msedgedriver.exe");
        WebDriver driver = new EdgeDriver();
        driver.manage().window().maximize();

        try {
            driver.get("http://localhost:4200");

            ((JavascriptExecutor) driver).executeScript(
                "localStorage.setItem('token','fake-token');" +
                "localStorage.setItem('userRole','KUPAC');" +
                "localStorage.setItem('username','seleniumKupac');"
            );

            driver.navigate().to("http://localhost:4200/pocetna");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            WebElement logoutBtn = wait.until(
                ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(text(),'ODJAVI SE')]")
                )
            );
            logoutBtn.click();

            wait.until(ExpectedConditions.urlContains("/login"));

            Object token = ((JavascriptExecutor) driver)
                    .executeScript("return localStorage.getItem('token');");

            if (token == null) {
                System.out.println("✅ Logout test PROŠAO");
            } else {
                System.out.println("❌ Token još postoji – logout NIJE uspeo");
            }

        } finally {
            driver.quit();
        }
    }
}
