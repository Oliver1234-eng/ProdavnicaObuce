package com.vts.prodavnicaobuce.selenium;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class ZavrsiKupovinuTest {

    @Test
    void testZavrsiKupovinu() {
        System.setProperty("webdriver.edge.driver", "C:\\drivers\\msedgedriver.exe");
        WebDriver driver = new EdgeDriver();
        driver.manage().window().maximize();

        try {
            driver.get("http://localhost:4200");

            ((JavascriptExecutor) driver).executeScript("""
                localStorage.setItem('userRole','KUPAC');
                localStorage.setItem('username','seleniumKupac');
                localStorage.setItem('korpa', JSON.stringify([
                    { naziv: 'Patike A', cena: 10000, velicina: 42 },
                    { naziv: 'Patike B', cena: 12000, velicina: 43 }
                ]));
            """);

            driver.navigate().to("http://localhost:4200/pocetna");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            WebElement cartIcon = wait.until(
                ExpectedConditions.elementToBeClickable(By.className("cart-icon"))
            );
            cartIcon.click();

            WebElement finishBtn = wait.until(
                ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(text(),'ZAVRŠI KUPOVINU')]")
                )
            );

            finishBtn.click();

            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            String alertText = alert.getText();
            alert.accept();

            if (alertText.contains("Kupovina je uspešno izvršena")) {
                System.out.println("✅ Alert poruka OK");
            } else {
                System.out.println("❌ Pogrešna alert poruka");
            }

            wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.id("korpaModal")
            ));

            cartIcon.click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(),'korpa je trenutno prazna')]")
            ));

            System.out.println("✅ Završi kupovinu test PROŠAO");

        } finally {
            driver.quit();
        }
    }
}
