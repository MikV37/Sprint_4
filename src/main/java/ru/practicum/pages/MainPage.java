package ru.practicum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private WebDriver driver;
    private static final String BASE_URL = "";

    // Локаторы для заказа
    private final By orderButton = By.xpath(".//button[contains(text(),'Заказать')]");
    private final By successMessage = By.xpath("//*[contains(text(), 'Заказ оформлен')]");
    private final By submitOrderButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    private final By confirmYesButton = By.xpath(".//button[contains(text(),'Да')]");

    // Локаторы для вопросов
    private final String questionXpathTemplate = "//div[@id='accordion__heading-%d']";
    private final String answerXpathTemplate = "//div[@id='accordion__panel-%d']";

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    // Общий шаг - открыть страницу
    public void openPage() {
        driver.get(BASE_URL);
    }

    // Методы для заказа
    public void clickOrderButtonTest() {
        driver.findElement(orderButton).click();
    }

    public boolean isOrderSuccessTest() {
        try {
            return driver.findElement(successMessage).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void submitOrder() {
        driver.findElement(submitOrderButton).click();
        driver.findElement(confirmYesButton).click();
    }
    public void clickQuestionTest(int index) {
        String xpath = String.format(questionXpathTemplate, index);
        WebElement questionElement = driver.findElement(By.xpath(xpath));

        // Скроллим к элементу, чтобы он был в видимой области
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", questionElement);

        // Ждём, пока элемент станет кликабельным
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(questionElement)).click();
    }

    public String getQuestionTextTest(int index) {
        String xpath = String.format(questionXpathTemplate, index);
        return driver.findElement(By.xpath(xpath)).getText().trim();
    }

    public String getAnswerTextTest(int index) {
        String xpath = String.format(answerXpathTemplate, index);
        return driver.findElement(By.xpath(xpath)).getText().trim();
    }
}

