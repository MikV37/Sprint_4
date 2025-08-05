package ru.practicum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DropDownListPage {
    private WebDriver driver;

    // локаторы для вопросов и ответов (если нужно)
    private final String questionXpathTemplate = "//div[@id='accordion__heading-%d']";
    private final String answerXpathTemplate = "//div[@id='accordion__panel-%d']";

    public DropDownListPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickQuestionTest(int index) {
        String xpath = String.format(questionXpathTemplate, index);
        driver.findElement(By.xpath(xpath)).click();
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