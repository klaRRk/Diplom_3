package webpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

public class RegisterPage {

    private WebDriver driver;
    private By nameField = By.xpath(".//label[text()='Имя']/../input");
    private By emailField = By.xpath(".//label[text()='Email']/../input");
    private By passwordField = By.xpath(".//label[text()='Пароль']/../input");
    private By regButton = By.xpath("//button[contains(text(),'Зарегистрироваться')]");
    private By loginButton = By.xpath(".//a[text()='Войти']");
    private By pwError = By.xpath(".//p[text()='Некорректный пароль']");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setNameField(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    public void setEmailField(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public void setPasswordField(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickRegButton() {
        driver.findElement(regButton).click();
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public void setClientInfo(String name, String email, String password) {
        setNameField(name);
        setEmailField(email);
        setPasswordField(password);
    }

    public String getPwErrorText() {
        return driver.findElement(pwError).getText();
    }
}