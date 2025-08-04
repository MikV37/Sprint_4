package ru.practicum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.practicum.pages.MainPage;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.assertTrue;
import static ru.practicum.util.Config.BASE_URL;

@RunWith(Parameterized.class)
public class OrderScooterTest extends BaseTest {

    private String firstName;
    private String lastName;
    private String address;
    private String metroStation;
    private String phone;
    private String date;
    private String rentalPeriod;
    private String color;
    private String comment;
    private boolean useBottomButton;

    public OrderScooterTest(
            String firstName, String lastName, String address, String metroStation,
            String phone, String date, String rentalPeriod, String color, String comment, boolean useBottomButton) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.date = date;
        this.rentalPeriod = rentalPeriod;
        this.color = color;
        this.comment = comment;
        this.useBottomButton = useBottomButton;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][]{
                {"Михаил", "Самокатов", "Фрунзе 55", "4", "89009009090", "03.08.2025", "пятеро суток", "black", "Позвонить за час", false},
                {"Алексей", "Иванов", "Ленинградская 10", "2", "89991234567", "04.08.2025", "трое суток", "grey", "Везите быстрее", true},
                {"Анна", "Петрова", "Пушкина 1", "1", "89001234567", "05.08.2025", "двое суток", "black", "Срочно", false}
        });
    }

    @Test
    public void testOrder(){
        driver.get(BASE_URL);

        MainPage mainPage = new MainPage(driver);

        // Выбор метода открытия формы заказа
        if (useBottomButton) {
            mainPage.clickOrderButtonAtBottom();
        } else {
            mainPage.clickOrderButton();
        }
        // Заполнение формы с параметрами
        mainPage.fillFirstName(firstName);
        mainPage.fillLastName(lastName);
        mainPage.fillAddress(address);
        mainPage.selectMetroStation(metroStation);
        mainPage.fillPhone(phone);
        mainPage.clickNext();

        mainPage.fillDate(date);
        mainPage.selectRentalPeriod(rentalPeriod);
        mainPage.selectColor(color);
        mainPage.fillComment(comment);
        mainPage.submitOrder();

        assertTrue(mainPage.isOrderSuccess());
    }
}