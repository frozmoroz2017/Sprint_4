    package ru.practicum.pages;

    import org.openqa.selenium.By;
    import org.openqa.selenium.WebDriver;
    import org.openqa.selenium.support.ui.ExpectedConditions;
    import org.openqa.selenium.support.ui.WebDriverWait;

    import java.time.Duration;

    import static ru.practicum.util.EnvConfig.BASE_URL;
    import static ru.practicum.util.EnvConfig.EXPLICITY_TIMEOUT;

    public class MainPage {

        private final WebDriver driver;
        private final By goButton = By.cssSelector(".Button_Button__ra12g.Header_Button__28dPO");
        private final By searchField =  By.cssSelector(".Input_Input__1iN_Z.Header_Input__xIoUq");
        private final By statusButton = By.cssSelector(".Header_Link__1TAG7");


        private final By orderButtonTop = By.cssSelector(".Button_Button__ra12g"); // Верхняя кнопка "Заказать"
        private final By orderButtonBottom = By.xpath("//button[contains(@class, 'Button_Middle__1CSJM')]"); // Нижняя кнопка "Заказать"


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
        }



        public StatusPage ClickGoButton() {

            new WebDriverWait(driver, Duration.ofSeconds(EXPLICITY_TIMEOUT))
                    .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(goButton));
            driver.findElement(goButton).click();
            return new StatusPage(driver);
        }

        public void SendOrderNumber() {
            driver.findElement(searchField).sendKeys("123");
        }

        public void ClickOnStatusButton() {
            driver.findElement(statusButton).click();
        }

        public void OpenMainPage() {
            driver.get(BASE_URL);
        }
        public void clickQuestion(int index) {
            new WebDriverWait(driver, Duration.ofSeconds(EXPLICITY_TIMEOUT))
                    .until(ExpectedConditions.elementToBeClickable(questionButtons[index]));
            driver.findElement(questionButtons[index]).click();
        }

        public String getAnswerText(int index) {
            new WebDriverWait(driver, Duration.ofSeconds(EXPLICITY_TIMEOUT))
                    .until(ExpectedConditions.visibilityOfElementLocated(answerTexts[index]));
            return driver.findElement(answerTexts[index]).getText();
        }


        public OrderPage clickOrderButtonTop() {
            new WebDriverWait(driver, Duration.ofSeconds(EXPLICITY_TIMEOUT))
                    .until(ExpectedConditions.elementToBeClickable(orderButtonTop));
            driver.findElement(orderButtonTop).click();
            return new OrderPage(driver);
        }

        public OrderPage clickOrderButtonBottom() {
            new WebDriverWait(driver, Duration.ofSeconds(EXPLICITY_TIMEOUT))
                    .until(ExpectedConditions.elementToBeClickable(orderButtonBottom));
            driver.findElement(orderButtonBottom).click();
            return new OrderPage(driver);
        }
        public By getQuestionLocator(int index) {
            return questionButtons[index];
        }

        public By getAnswerLocator(int index) {
            return answerTexts[index];
        }
        public void closeCookieBanner() {
            try {
                new WebDriverWait(driver, Duration.ofSeconds(EXPLICITY_TIMEOUT))
                        .until(ExpectedConditions.elementToBeClickable(
                                By.id("rcc-confirm-button"))).click();
            } catch (Exception e) {
                System.out.println("Cookie banner not found or already closed");
            }
        }

    }

