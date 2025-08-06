package ru.practicum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static ru.practicum.util.EnvConfig.EXPLICITY_TIMEOUT;

public class StatusPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final By errorImage = By.cssSelector("img[alt='Not found']");

    public StatusPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICITY_TIMEOUT));
    }

    public boolean checkErrorImage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(errorImage));
        return driver.findElement(errorImage).isDisplayed();
    }
}