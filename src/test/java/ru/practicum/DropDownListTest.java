package ru.practicum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.practicum.pages.MainPage;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static ru.practicum.util.Config.BASE_URL;

@RunWith(Parameterized.class)
public class DropDownListTest extends BaseTest {

    // Тестовые данные в виде констант
    private static final Object[][] TEST_DATA = {
            {1, "Хочу сразу несколько самокатов! Так можно?", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
            {2, "Как рассчитывается время аренды?", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
            {3, "Можно ли заказать самокат прямо на сегодня?", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
            {4, "Можно ли продлить заказ или вернуть самокат раньше?", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
            {5, "Вы привозите зарядку вместе с самокатом?", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
            {6, "Можно ли отменить заказ?", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
            {7, "Я жизу за МКАДом, привезёте?", "Да, обязательно. Всем самокатов! И Москве, и Московской области."}
    };

    @Parameterized.Parameters(name = "Тестовые данные: {0} - {1}")
    public static Collection<Object[]> getTestData() {
        return Arrays.asList(TEST_DATA);
    }

    private int index;
    private String expectedQuestion;
    private String expectedAnswer;

    public DropDownListTest(int index, String expectedQuestion, String expectedAnswer) {
        this.index = index;
        this.expectedQuestion = expectedQuestion;
        this.expectedAnswer = expectedAnswer;
    }

    @Test
    public void questionsAccordionTest() {
        // Шаг 1: Открыть страницу
        driver.get(BASE_URL);

        // Шаг 2: Создать страницу и открыть нужный вопрос
        MainPage mainPage = new MainPage(driver);
        mainPage.clickQuestionTest(index); // кликнуть по вопросу

        // Шаг 3: Проверка - правильный вопрос и ответ отображаются
        String actualQuestion = mainPage.getQuestionTextTest(index);
        String actualAnswer = mainPage.getAnswerTextTest(index);

        // Проверка вопроса
        assertEquals("Проверка текста вопроса", expectedQuestion, actualQuestion);
        // Проверка ответа
        assertEquals("Проверка текста ответа", expectedAnswer, actualAnswer);
    }
}