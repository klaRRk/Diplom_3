package webpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

public class ForgotPwPage { // Объявляем публичный класс ForgotPasswordPage
    private WebDriver driver; // Объявляем переменную driver типа WebDriver
    private By loginButton = By.xpath(".//a[text()='Войти']"); // Объявляем локатор для кнопки "Войти"

    public ForgotPwPage(WebDriver driver) { // Создаем конструктор класса с параметром driver
        this.driver = driver; // Присваиваем значение параметра driver переменной класса
    }

    public void clickLoginButton() { // Объявляем метод для клика по кнопке "Войти"
        driver.findElement(loginButton).click(); // Находим кнопку по локатору и кликаем по ней
    }
}