package ru.practicum;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.practicum.pages.MainPage;
import ru.practicum.pages.OrderPage;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class OrderTest {

    @Rule
    public DriverFactory factory = new DriverFactory();

    private final String name;
    private final String lastName;
    private final String address;
    private final String metro;
    private final String phone;
    private final String date;
    private final String period;
    private final String color;
    private final String comment;
    private final boolean fromTopButton;

    public OrderTest(String name, String lastName, String address, String metro,
                     String phone, String date, String period, String color,
                     String comment, boolean fromTopButton) {
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
        this.date = date;
        this.period = period;
        this.color = color;
        this.comment = comment;
        this.fromTopButton = fromTopButton;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {"Макс", "Рокотански", "ул. Пустоши", "Черкизовская", "89991112233",
                        "03.08.2025", "сутки", "black", "Посредственно", true},
                {"Кай", "Метов", "Пушкина, 69", "Сокольники", "89994445566",
                        "10.08.2023", "двое суток", "grey", "Position 2", false}
        });
    }

    @Test
    public void testOrderScooter() {
        MainPage mainPage = new MainPage(factory.getDriver());
        mainPage.openMainPage();
        mainPage.closeCookieBanner();

        if (!fromTopButton) {
            mainPage.scrollToBottom();
            mainPage.clickBottomOrderButton();
        }

        OrderPage orderPage = fromTopButton ?
                mainPage.clickOrderButtonTop() :
                mainPage.clickOrderButtonBottom();

        orderPage.fillFirstPage(name, lastName, address, metro, phone);
        orderPage.fillSecondPage(date, period, color, comment);
        orderPage.confirmOrder();

        assert orderPage.isOrderSuccess();
    }
}