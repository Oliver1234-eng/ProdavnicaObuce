package com.vts.prodavnicaobuce.selenium;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class UkloniArtikalIzKorpeTest {

    @Test
    void testUkloniArtikal() {
        System.setProperty("webdriver.edge.driver", "C:\\drivers\\msedgedriver.exe");
        WebDriver driver = new EdgeDriver();
        driver.manage().window().maximize();

        try {
            driver.get("http://localhost:4200");

            ((JavascriptExecutor) driver).executeScript("""
                localStorage.setItem('userRole','KUPAC');
                localStorage.setItem('username','seleniumKupac');
                localStorage.setItem('korpa', JSON.stringify([
                  { naziv: 'Patike Test', cena: 12000, velicina: 42 }
                ]));
            """);

            driver.navigate().to("http://localhost:4200/pocetna");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            WebElement cartIcon = wait.until(
                ExpectedConditions.elementToBeClickable(By.className("cart-icon"))
            );
            cartIcon.click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("#korpaModal tbody tr"))
            );

            WebElement deleteBtn = driver.findElement(
                By.cssSelector("button.btn-outline-danger")
            );
            deleteBtn.click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(),'korpa je trenutno prazna')]")
            ));

            System.out.println("Uklanjanje artikla – TEST PROŠAO");

        } finally {
            driver.quit();
        }
    }
}
