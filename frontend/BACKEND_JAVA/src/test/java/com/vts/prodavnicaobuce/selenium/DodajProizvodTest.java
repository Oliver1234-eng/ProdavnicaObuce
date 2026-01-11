package com.vts.prodavnicaobuce.selenium;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class DodajProizvodTest {

    @Test
    void testDodavanjeProizvoda() {
        System.setProperty("webdriver.edge.driver", "C:\\drivers\\msedgedriver.exe");
        WebDriver driver = new EdgeDriver();
        driver.manage().window().maximize();

        try {
            driver.get("http://localhost:4200");

            ((JavascriptExecutor) driver).executeScript(
                "localStorage.setItem('userRole','PRODAVAC');" +
                "localStorage.setItem('username','seleniumAdmin');"
            );

            driver.navigate().to("http://localhost:4200/proizvodi");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            WebElement dodajBtn = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//button[contains(text(),'DODAJ NOVI PROIZVOD')]")
                )
            );
            dodajBtn.click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("naziv")));

            driver.findElement(By.name("naziv")).sendKeys("Selenium Patike");
            driver.findElement(By.name("cena")).sendKeys("15000");
            driver.findElement(By.name("velicina")).sendKeys("42");

            new Select(driver.findElement(By.name("kategorija")))
                    .selectByVisibleText("PATIKE");

            driver.findElement(By.name("stanje")).sendKeys("5");

            driver.findElement(By.xpath("//button[contains(text(),'SAČUVAJ')]")).click();

            wait.until(ExpectedConditions.alertIsPresent());
            String alert = driver.switchTo().alert().getText();
            driver.switchTo().alert().accept();

            System.out.println("ALERT: " + alert);

        } finally {
            driver.quit();
        }
    }
}
