     package ru.practicum.pages;

     import org.openqa.selenium.*;
     import org.openqa.selenium.support.ui.ExpectedConditions;
     import org.openqa.selenium.support.ui.WebDriverWait;

     import java.time.Duration;

    import static ru.practicum.util.EnvConfig.EXPLICITY_TIMEOUT;

    public class OrderPage {
        private final WebDriver driver;
        private final WebDriverWait wait;


        private final By nameField = By.xpath("//input[@placeholder='* Имя']");
        private final By lastNameField = By.xpath("//input[@placeholder='* Фамилия']");
        private final By addressField = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
        private final By metroField = By.xpath("//input[@placeholder='* Станция метро']");
        private final By phoneField = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
        private final By nextButton = By.xpath("//button[text()='Далее']");


        private final By dateField = By.xpath("//input[@placeholder='* Когда привезти самокат']");
        private final By rentalPeriodField = By.className("Dropdown-placeholder");
        private final By colorBlackCheckbox = By.id("black");
        private final By colorGreyCheckbox = By.id("grey");
        private final By commentField = By.xpath("//input[@placeholder='Комментарий для курьера']");
        private final By orderButton = By.xpath("//button[contains(@class, 'Button_Middle__1CSJM') and text()='Заказать']");


        private final By confirmButton = By.xpath("//button[text()='Да']");
        private final By successMessage = By.xpath("//div[contains(text(), 'Заказ оформлен')]");

        public OrderPage(WebDriver driver) {
            this.driver = driver;
            this.wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICITY_TIMEOUT));
        }

        public void fillFirstPage(String name, String lastName, String address, String metro, String phone) {
            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(nameField)).sendKeys(name);
                driver.findElement(lastNameField).sendKeys(lastName);
                driver.findElement(addressField).sendKeys(address);


                WebElement metroElement = wait.until(ExpectedConditions.elementToBeClickable(metroField));
                metroElement.click();
                metroElement.sendKeys(metro);
                wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath(String.format("//div[text()='%s']", metro)))).click();

                driver.findElement(phoneField).sendKeys(phone);
                wait.until(ExpectedConditions.elementToBeClickable(nextButton)).click();
            } catch (Exception e) {
                throw new RuntimeException("Failed to fill first page: " + e.getMessage(), e);
            }
        }

        public void fillSecondPage(String date, String period, String color, String comment) {
            try {

                wait.until(ExpectedConditions.visibilityOfElementLocated(dateField));


                WebElement dateElement = driver.findElement(dateField);
                dateElement.click();
                dateElement.clear();
                dateElement.sendKeys(date);
                dateElement.sendKeys(Keys.ENTER);


                wait.until(ExpectedConditions.elementToBeClickable(rentalPeriodField)).click();
                wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath(String.format("//div[text()='%s']", period)))).click();


                if ("black".equals(color)) {
                    wait.until(ExpectedConditions.elementToBeClickable(colorBlackCheckbox)).click();
                } else {
                    wait.until(ExpectedConditions.elementToBeClickable(colorGreyCheckbox)).click();
                }


                driver.findElement(commentField).sendKeys(comment);


                ((JavascriptExecutor) driver).executeScript(
                        "arguments[0].scrollIntoView(true);",
                        driver.findElement(orderButton));
                wait.until(ExpectedConditions.elementToBeClickable(orderButton)).click();
            } catch (Exception e) {
                throw new RuntimeException("Failed to fill second page: " + e.getMessage(), e);
            }
        }

        public void confirmOrder() {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(confirmButton)).click();
            } catch (Exception e) {
                throw new RuntimeException("Failed to confirm order: " + e.getMessage(), e);
            }
        }

        public boolean isOrderSuccess() {
            try {
                return wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage)).isDisplayed();
            } catch (Exception e) {
                throw new RuntimeException("Failed to verify order success: " + e.getMessage(), e);
            }
        }
    }