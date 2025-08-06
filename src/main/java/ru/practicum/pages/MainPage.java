package ru.practicum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static ru.practicum.util.EnvConfig.BASE_URL;
import static ru.practicum.util.EnvConfig.EXPLICITY_TIMEOUT;

public class MainPage {
    private final WebDriver driver;
    private final WebDriverWait wait;


    private final By goButton = By.cssSelector(".Button_Button__ra12g.Header_Button__28dPO");
    private final By searchField = By.cssSelector(".Input_Input__1iN_Z.Header_Input__xIoUq");
    private final By statusButton = By.cssSelector(".Header_Link__1TAG7");
    private final By cookieBanner = By.id("rcc-confirm-button");


    private final By orderButtonTop = By.cssSelector(".Button_Button__ra12g");
    private final By orderButtonBottom = By.xpath("//button[contains(@class, 'Button_Middle__1CSJM')]");
    private final By bottomOrderButton = By.xpath("//button[contains(@class, 'Button_UltraBig__UU3Lp') and text()='Заказать']");


    private final By[] questionButtons = {
            By.id("accordion__heading-0"),
            By.id("accordion__heading-1"),
            By.id("accordion__heading-2"),
            By.id("accordion__heading-3"),
            By.id("accordion__heading-4"),
            By.id("accordion__heading-5"),
            By.id("accordion__heading-6"),
            By.id("accordion__heading-7")
    };

    private final By[] answerTexts = {
            By.id("accordion__panel-0"),
            By.id("accordion__panel-1"),
            By.id("accordion__panel-2"),
            By.id("accordion__panel-3"),
            By.id("accordion__panel-4"),
            By.id("accordion__panel-5"),
            By.id("accordion__panel-6"),
            By.id("accordion__panel-7")
    };

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICITY_TIMEOUT));
    }


    public void openMainPage() {
        driver.get(BASE_URL);
    }

    public void closeCookieBanner() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(cookieBanner)).click();
        } catch (Exception e) {
            System.out.println("Cookie banner not found or already closed");
        }
    }


    public StatusPage clickGoButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(goButton)).click();
        return new StatusPage(driver);
    }

    public void sendOrderNumber(String orderNumber) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchField)).sendKeys(orderNumber);
    }

    public void clickOnStatusButton() {
        wait.until(ExpectedConditions.elementToBeClickable(statusButton)).click();
    }


    public OrderPage clickOrderButtonTop() {
        wait.until(ExpectedConditions.elementToBeClickable(orderButtonTop)).click();
        return new OrderPage(driver);
    }

    public OrderPage clickOrderButtonBottom() {
        wait.until(ExpectedConditions.elementToBeClickable(orderButtonBottom)).click();
        return new OrderPage(driver);
    }

    public void scrollToBottom() {
        ((JavascriptExecutor) driver).executeScript(
                "window.scrollTo(0, document.body.scrollHeight)");
    }

    public void clickBottomOrderButton() {
        wait.until(ExpectedConditions.elementToBeClickable(bottomOrderButton)).click();
    }


    public void scrollToQuestion(int index) {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView(true);",
                driver.findElement(questionButtons[index]));
    }

    public void clickQuestion(int index) {
        wait.until(ExpectedConditions.elementToBeClickable(questionButtons[index])).click();
    }

    public String getAnswerText(int index) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(answerTexts[index])).getText();
    }


    public By getQuestionLocator(int index) {
        return questionButtons[index];
    }

    public By getAnswerLocator(int index) {
        return answerTexts[index];
    }
}