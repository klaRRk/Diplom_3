import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class Browser {

    protected WebDriver driver;
    public static final String PROPERTIES = "src/main/resources/config.properties";
    private static String browser;
    private static String chromeDriverPath;
    private static String yandexDriverPath;

    @Before
    public void configure() {
        Properties prop = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream(PROPERTIES)) {
            prop.load(fileInputStream);
            browser = prop.getProperty("browser");
            chromeDriverPath = prop.getProperty("chromeDriverPath", "src/main/resources/chromedriver.exe");
            yandexDriverPath = prop.getProperty("yandexDriverPath", "src/main/resources/yandexdriver.exe");
        } catch (IOException e) {
            e.printStackTrace();
        }
        selectBrowser();
    }

    public void selectBrowser() {
        switch (browser.toLowerCase()) {
            case "chrome":
                setUpDriver(chromeDriverPath);
                break;
            case "yandex":
                setUpDriver(yandexDriverPath);
                break;
            default:
                throw new IllegalArgumentException("Unknown browser: " + browser);
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    private void setUpDriver(String driverPath) {
        ChromeOptions options = new ChromeOptions();
        System.setProperty("webdriver.chrome.driver", driverPath);
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
    }
}
