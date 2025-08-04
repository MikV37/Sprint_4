package ru.practicum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class MainPage {
    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }
    // Открытие формы заказа
    // Метод для открытия формы заказа через верхнюю кнопку
    public void clickOrderButton() {
        driver.findElement(By.xpath(".//button[contains(text(),'Заказать')]")).click();
    }

    // Метод для открытия формы заказа через нижнюю кнопку (с прокруткой)
    public void clickOrderButtonAtBottom() {
        By bottomButtonLocator = By.xpath(".//div[@class = 'Home_FinishButton__1_cWm']/button");
        WebElement bottomButton = driver.findElement(bottomButtonLocator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", bottomButton);
        bottomButton.click();
    }

    // Методы для заполнения формы
    // Ввод Имени
    public void fillFirstName(String firstName) {
        driver.findElement(By.xpath(".//input[@placeholder='* Имя']")).sendKeys(firstName);
    }
    // Ввод фамилии
    public void fillLastName(String lastName) {
        driver.findElement(By.xpath(".//input[@placeholder='* Фамилия']")).sendKeys(lastName);
    }
    // Ввод адреса
    public void fillAddress(String address) {
        driver.findElement(By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']")).sendKeys(address);
    }
    // Выбор станции по номеру
    public void selectMetroStation(String station) {
        driver.findElement(By.xpath(".//div[@class='select-search__value']")).click();
        driver.findElement(By.xpath(".//button[@value='" + station + "']")).click();
    }
    // Ввод номера телефона
    public void fillPhone(String phone) {
        driver.findElement(By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']")).sendKeys(phone);
    }
    // Переход на форму аренды
    public void clickNext() {
        driver.findElement(By.xpath(".//button[contains(text(),'Далее')]")).click();
    }
    // Методы для заполнения формы аренды
    public void fillDate(String date) {
        driver.findElement(By.cssSelector(".react-datepicker__input-container")).click();
        driver.findElement(By.cssSelector(".Input_Input__1iN_Z.Input_Responsible__1jDKN.react-datepicker-ignore-onclickoutside"))
                .sendKeys(date + "\n");
    }
    // Выбор периода аренды
    public void selectRentalPeriod(String period) {
        driver.findElement(By.cssSelector(".Dropdown-arrow")).click();
        driver.findElement(By.xpath(".//div[@class='Dropdown-menu']//div[text()='" + period + "']")).click();
    }
    // Выбор самоката по цвету
    public void selectColor(String color) {
        String locator = "";
        if (color.equalsIgnoreCase("black")) {
            locator = ".//input[@id='black']";
        } else if (color.equalsIgnoreCase("grey")) {
            locator = ".//input[@id='grey']";
        }
        driver.findElement(By.xpath(locator)).click();
    }
    // Ввод комментария
    public void fillComment(String comment) {
        driver.findElement(By.xpath(".//input[@placeholder='Комментарий для курьера']")).sendKeys(comment);
    }
    // Методы завершения заказа
    public void submitOrder() {
        driver.findElement(By.xpath(".//button[@class = 'Button_Button__ra12g Button_Middle__1CSJM']")).click();
        driver.findElement(By.xpath(".//button[contains(text(),'Да')]")).click();
    }
    // Проверка оформления заказа
    public boolean isOrderSuccess() {
        try {
            driver.findElement(By.xpath("//*[contains(text(), 'Заказ оформлен')]"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}