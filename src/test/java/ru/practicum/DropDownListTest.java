package ru.practicum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.junit.Assert;

import java.util.Arrays;
import java.util.Collection;

import static ru.practicum.util.Config.BASE_URL;

@RunWith(Parameterized.class)
public class DropDownListTest extends BaseTest {
    private int index;
    private String expectedQuestion;
    private String expectedAnswer;


    // Конструктор для параметризованного теста
    public DropDownListTest(int index, String expectedQuestion, String expectedAnswer) {
        this.index = index;
        this.expectedQuestion = expectedQuestion;
        this.expectedAnswer = expectedAnswer;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {0, "Сколько это стоит? И как оплатить?", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {1, "Хочу сразу несколько самокатов! Так можно?", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {2, "Как рассчитывается время аренды?", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {3, "Можно ли заказать самокат прямо на сегодня?", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {4, "Можно ли продлить заказ или вернуть самокат раньше?", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {5, "Вы привозите зарядку вместе с самокатом?", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {6, "Можно ли отменить заказ?", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {7, "Я жизу за МКАДом, привезёте?", "Да, обязательно. Всем самокатов! И Москве, и Московской области."}
        });
    }

    @Test
    public void testAccordionQuestions() {
        driver.get(BASE_URL);
        checkQuestionAnswer(index, expectedQuestion, expectedAnswer);
    }

    private void checkQuestionAnswer(int index, String expectedQuestion, String expectedAnswer) {
        // Находим и кликаем по вопросу
        WebElement question = driver.findElement(By.xpath(String.format("//div[@id='accordion__heading-%d']", index)));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", question);
        question.click();

        // Проверяем вопрос
        Assert.assertEquals(expectedQuestion, question.getText().trim());

        // Проверяем ответ
        String answerText = driver.findElement(By.xpath(String.format("//div[@id='accordion__panel-%d']", index))).getText().trim();
        Assert.assertEquals(expectedAnswer, answerText);
    }
}