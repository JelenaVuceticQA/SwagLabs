package Base;

import Pages.HomePage;
import Pages.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.time.Duration;

public class BaseTest {

//Definisemo promenljive
    public static WebDriver driver;
    public WebDriver driver() {
        return driver;
    }
    public String homeURL = "https://www.saucedemo.com/";
    public WebDriverWait wait;

    public LoginPage loginPage;
    public HomePage homePage;

    //postavljamo drajvere i ova anotacija se moze korititi bilo gde pozivom na @Test
    @BeforeClass
    public void setUp() throws IOException {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();


        Duration duration = Duration.ofSeconds(20);
        driver.manage().timeouts().implicitlyWait(duration.getSeconds(), java.util.concurrent.TimeUnit.SECONDS);

        loginPage = new LoginPage();
        homePage = new HomePage();
    }
}
