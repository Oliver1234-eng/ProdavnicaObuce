package com.vts.prodavnicaobuce.selenium;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ResgistracijaTest {
    @Test
    void testRegistracijaEdge() {
        System.setProperty("webdriver.edge.driver", "C:\\drivers\\msedgedriver.exe");
        WebDriver driver = new EdgeDriver();
        driver.manage().window().maximize();

        try {
            driver.get("http://localhost:4200/registracija");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement ulogaDropdown = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.name("uloga"))
            );

            Select ulogaSelect = new Select(ulogaDropdown);
            ulogaSelect.selectByVisibleText("Prodavac");

            driver.findElement(By.name("ime")).sendKeys("Marko");
            driver.findElement(By.name("prezime")).sendKeys("Markovic");
            driver.findElement(By.name("email")).sendKeys("marko.test@example.com");
            driver.findElement(By.name("adresa")).sendKeys("Neka Ulica 12");
            driver.findElement(By.name("brojTelefona")).sendKeys("0612345678");
            driver.findElement(By.name("username")).sendKeys("MarkoTest123");
            driver.findElement(By.name("password")).sendKeys("TestPass*123");

            driver.findElement(By.className("btn-reg")).click();

            wait.until(ExpectedConditions.alertIsPresent());

            String alertText = driver.switchTo().alert().getText();
            System.out.println("Alert text: " + alertText);
            driver.switchTo().alert().accept();

            if (alertText.contains("Uspešna registracija")) {
                System.out.println("Registracija test prošao!");
            } else {
                System.out.println("Registracija test nije prošao!");
            }

            Thread.sleep(5000);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           driver.quit();
        }
    }
}
