    package ru.practicum;

    import org.junit.Rule;
    import org.junit.Test;
    import org.openqa.selenium.By;
    import org.openqa.selenium.JavascriptExecutor;
    import org.openqa.selenium.WebDriver;
    import org.openqa.selenium.support.ui.ExpectedConditions;
    import org.openqa.selenium.support.ui.WebDriverWait;
    import ru.practicum.pages.MainPage;

    import java.time.Duration;

    import static ru.practicum.util.EnvConfig.EXPLICITY_TIMEOUT;

    public class FaqTest {

        @Rule
        public DriverFactory factory = new DriverFactory();

        @Test
        public void testFaqItems() {
            WebDriver driver = factory.getDriver();
            MainPage mainPage = new MainPage(driver);
            mainPage.OpenMainPage();


            try {
                new WebDriverWait(driver, Duration.ofSeconds(EXPLICITY_TIMEOUT))
                        .until(ExpectedConditions.elementToBeClickable(
                                By.id("rcc-confirm-button"))).click();
            } catch (Exception e) {

            }

            for (int i = 0; i < 8; i++) {

                ((JavascriptExecutor) driver).executeScript(
                        "arguments[0].scrollIntoView(true);",
                        driver.findElement(mainPage.getQuestionLocator(i)));


                new WebDriverWait(driver, Duration.ofSeconds(EXPLICITY_TIMEOUT))
                        .until(ExpectedConditions.elementToBeClickable(
                                mainPage.getQuestionLocator(i))).click();


                String answer = new WebDriverWait(driver, Duration.ofSeconds(EXPLICITY_TIMEOUT))
                        .until(ExpectedConditions.visibilityOfElementLocated(
                                mainPage.getAnswerLocator(i))).getText();


                assert !answer.isEmpty() : "Ответ на вопрос " + (i+1) + " пустой";
            }
        }
    }