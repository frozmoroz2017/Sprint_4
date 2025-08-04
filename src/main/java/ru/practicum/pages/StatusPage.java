    package ru.practicum.pages;

    import org.openqa.selenium.By;
    import org.openqa.selenium.WebDriver;
    import org.openqa.selenium.support.ui.ExpectedConditions;
    import org.openqa.selenium.support.ui.WebDriverWait;

    import java.time.Duration;

    import static ru.practicum.util.EnvConfig.EXPLICITY_TIMEOUT;

    public class StatusPage {
        private final WebDriver driver;
        private final By img = By.cssSelector("img[alt='Not found']");

        public StatusPage(WebDriver driver) {
            this.driver = driver;
        }

        public void CheckErrorImage() {
            new WebDriverWait(driver, Duration.ofSeconds(EXPLICITY_TIMEOUT))
                    .until(ExpectedConditions.visibilityOfElementLocated(img));

            if (!driver.findElement(img).isDisplayed()) {
                throw new RuntimeException("Error image is not displayed");
            }
        }
    }