package com.vts.prodavnicaobuce.selenium;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;

public class DodajUKorpuTest {

    @Test
    void testDodavanjeUKorpu() {
        System.setProperty("webdriver.edge.driver", "C:\\drivers\\msedgedriver.exe");
        WebDriver driver = new EdgeDriver();
        driver.manage().window().maximize();

        try {
            driver.get("http://localhost:4200");
            
            ((JavascriptExecutor) driver).executeScript(
                "localStorage.setItem('userRole','KUPAC');" +
                "localStorage.setItem('username','seleniumKupac');"
            );

            driver.navigate().to("http://localhost:4200/proizvodi");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("card")));

            WebElement dodajUKorpuBtn = wait.until(
                ExpectedConditions.elementToBeClickable(
                    By.xpath("(//button[contains(text(),'DODAJ U KORPU')])[1]")
                )
            );
            dodajUKorpuBtn.click();

            WebElement korpaIkona = wait.until(
                ExpectedConditions.elementToBeClickable(By.className("cart-icon"))
            );
            korpaIkona.click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("korpaModal")));

            List<WebElement> stavke = driver.findElements(By.cssSelector("#korpaModal tbody tr"));

            if (stavke.size() > 0) {
                System.out.println("✅ Dodavanje u korpu – TEST PROŠAO");
            } else {
                System.out.println("❌ Korpa je prazna – TEST NIJE PROŠAO");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
