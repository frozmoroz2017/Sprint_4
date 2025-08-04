    package ru.practicum;

    import org.junit.Rule;
    import org.junit.Test;

    import ru.practicum.pages.MainPage;
    import ru.practicum.pages.StatusPage;


    public class ScooterOrderTest {

        @Rule
        public DriverFactory factory = new DriverFactory();

        @Test
        public void testNonExistingOrderNotFound() throws InterruptedException {
            var driver = factory.getDriver();
            var mainPage = new MainPage(driver);
            mainPage.OpenMainPage();
            mainPage.ClickOnStatusButton();
            mainPage.SendOrderNumber();
            StatusPage statusPage = mainPage.ClickGoButton();
            statusPage.CheckErrorImage();

        }


    }
