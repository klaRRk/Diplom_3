package webpages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

public class MainPage {
    private WebDriver driver;
    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    private By loginButton = By.xpath(".//button[text()='Войти в аккаунт']");
    private By profileButton = By.xpath(".//p[text()='Личный Кабинет']");
    private By bunButton = By.xpath(".//span[text()='Булки']");
    private By sauceButton = By.xpath(".//span[text()='Соусы']");
    private By fillingButton = By.xpath(".//span[text()='Начинки']");
    private By createOrderButton = By.xpath(".//button[text()='Оформить заказ']");
    private By menuTabLocator = By.xpath("//div[contains(@class,'tab_tab__1SPyG tab_tab_type_current__2BEPc')]");

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public void clickAccButton() {
        driver.findElement(profileButton).click();
    }

    public void clickBunButton() {
        driver.findElement(bunButton).click();
    }

    public void clickSauceButton() {
        driver.findElement(sauceButton).click();
    }

    public void clickFillingButton() {
        driver.findElement(fillingButton).click();
    }

    public String getCreateOrderButtonText(){
        return driver.findElement(createOrderButton).getText();
    }

    public String getMenuLocator(){
        return driver.findElement(menuTabLocator).getText();
    }
}