package ru.practicum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.practicum.pages.MainPage;
import ru.practicum.pages.OrderPage;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertTrue;
import static ru.practicum.util.Config.BASE_URL;

@RunWith(Parameterized.class)
public class OrderScooterTest extends BaseTest {

    // Тестовые данные в виде констант
    private static final Object[][] TEST_DATA = {
            {"Михаил", "Самокатов", "Фрунзе 55", "4", "89009009090", "03.08.2025", "пятеро суток", "black", "Позвонить за час"},
            {"Алексей", "Иванов", "Ленинградская 10", "2", "89991234567", "04.08.2025", "трое суток", "grey", "Везите быстрее"},
            {"Анна", "Петрова", "Пушкина 1", "1", "89001234567", "05.08.2025", "двое суток", "black", "Срочно"}
    };

    @Parameterized.Parameters(name = "Тестовые данные: {0} - {1}")
    public static Collection<Object[]> testData() {
        return Arrays.asList(TEST_DATA);
    }

    private String firstName;
    private String lastName;
    private String address;
    private String metroStation;
    private String phone;
    private String date;
    private String rentalPeriod;
    private String color;
    private String comment;

    public OrderScooterTest(
            String firstName, String lastName, String address, String metroStation,
            String phone, String date, String rentalPeriod, String color, String comment) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.date = date;
        this.rentalPeriod = rentalPeriod;
        this.color = color;
        this.comment = comment;
    }

    @Test
    public void TestOrder() {
        driver.get(BASE_URL);

        // Открываем страницу и кнопку заказа
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOrderButtonTest();

        // Создаем объект OrderPage для заполнения формы
        OrderPage orderPage = new OrderPage(driver);

        // Заполняем личную информацию и переходим далее
        orderPage.fillPersonalInfoTest(firstName, lastName, address, metroStation, phone)
                .clickNextTest()
                .fillOrderDetailsTest(date, rentalPeriod, color, comment);

        // Отправляем заказ
        orderPage.submitOrderTest();

        // Проверка успеха
        assertTrue(mainPage.isOrderSuccessTest());
    }
}