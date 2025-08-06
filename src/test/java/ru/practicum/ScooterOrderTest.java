package ru.practicum;

import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.practicum.pages.MainPage;
import ru.practicum.pages.StatusPage;

import static org.junit.Assert.assertTrue;

public class ScooterOrderTest {

    @Rule
    public DriverFactory factory = new DriverFactory();

    @Test
    public void testNonExistingOrderNotFound() {
        WebDriver driver = factory.getDriver();
        MainPage mainPage = new MainPage(driver);
        StatusPage statusPage = new StatusPage(driver);


        mainPage.openMainPage();
        mainPage.clickOnStatusButton();
        mainPage.sendOrderNumber("123");


        mainPage.clickGoButton();


        boolean isErrorDisplayed = statusPage.checkErrorImage();
        assertTrue("При несуществующем заказе система должна показать ошибку", isErrorDisplayed);
    }
}