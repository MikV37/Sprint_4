package ru.practicum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderPage {
    private WebDriver driver;

    // Локаторы для заполнения форм
    private By firstNameInput = By.xpath(".//input[@placeholder='* Имя']");
    private By lastNameInput = By.xpath(".//input[@placeholder='* Фамилия']");
    private By addressInput = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private By metroStationInput = By.xpath(".//div[@class='select-search__value']");
    private By metroStationButton(String station) {
        return By.xpath(".//button[@value='" + station + "']");
    }
    private By phoneInput = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    private By nextButton = By.xpath(".//button[contains(text(),'Далее')]");

    private By dateInput = By.cssSelector(".react-datepicker__input-container");
    private By dateInputField = By.cssSelector(".Input_Input__1iN_Z.Input_Responsible__1jDKN.react-datepicker-ignore-onclickoutside");
    private By rentalPeriodDropdown = By.cssSelector(".Dropdown-arrow");
    private By rentalPeriodOption(String period) {
        return By.xpath(".//div[@class='Dropdown-menu']//div[text()='" + period + "']");
    }
    private By colorBlackCheckbox = By.xpath(".//input[@id='black']");
    private By colorGreyCheckbox = By.xpath(".//input[@id='grey']");
    private By commentInput = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    private By submitButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    private By confirmYesButton = By.xpath(".//button[contains(text(),'Да')]");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    // Универсальный метод для заполнения персональных данных
    public OrderPage fillPersonalInfoTest(String firstName, String lastName, String address, String metroStation, String phone) {
        driver.findElement(firstNameInput).sendKeys(firstName);
        driver.findElement(lastNameInput).sendKeys(lastName);
        driver.findElement(addressInput).sendKeys(address);
        driver.findElement(metroStationInput).click();
        driver.findElement(metroStationButton(metroStation)).click();
        driver.findElement(phoneInput).sendKeys(phone);
        return this; // для цепочки вызовов
    }

    // Переход к следующему этапу заказа
    public OrderPage clickNextTest() {
        driver.findElement(nextButton).click();
        return this;
    }

    // Заполнение заказа для второго этапа
    public OrderPage fillOrderDetailsTest(String date, String rentalPeriod, String color, String comment) {
        driver.findElement(dateInput).click();
        driver.findElement(dateInputField).sendKeys(date + "\n");
        driver.findElement(rentalPeriodDropdown).click();
        driver.findElement(rentalPeriodOption(rentalPeriod)).click();

        if (color.equalsIgnoreCase("black")) {
            driver.findElement(colorBlackCheckbox).click();
        } else if (color.equalsIgnoreCase("grey")) {
            driver.findElement(colorGreyCheckbox).click();
        }
        driver.findElement(commentInput).sendKeys(comment);
        return this;
    }

    // Отправка формы и подтверждение
    public void submitOrderTest() {
        driver.findElement(submitButton).click();
        driver.findElement(confirmYesButton).click();
    }
}