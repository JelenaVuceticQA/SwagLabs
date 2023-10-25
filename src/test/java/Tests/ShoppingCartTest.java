package Tests;
import Pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import Base.BaseTest;
import Pages.LoginPage;
import Pages.AddItem;
import Pages.ShoppingCartPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ShoppingCartTest extends BaseTest {

    private LoginPage loginPage;
    private AddItem addItem;
    private ShoppingCartPage shoppingCartPage;
    private HomePage homePage;

    @BeforeMethod
    public void pageSetUp() {
        System.out.println("Setting up the page...");

        // inicijalizujemo
        loginPage = new LoginPage();
        addItem = new AddItem();
        shoppingCartPage = new ShoppingCartPage();
        homePage = new HomePage();

        driver.manage().window().maximize();
        System.out.println("Opening the home page: " + homeURL);
        driver.get(homeURL);
    }

    @Test(priority = 10)
    public void userCanAddRandomProductToCart() {
        try {
            loginPage.inputUsername("standard_user");
            loginPage.inputPassword("secret_sauce");
            loginPage.clickLoginButton();
            // Idi na stranicu sa proizvodima
            System.out.println("Navigating to inventory page...");
            driver.get("https://www.saucedemo.com/inventory.html");

            // Izaberi proizvod "Sauce Labs Backpack" i dodaj ga u korpu
            System.out.println("Adding product to the cart: Sauce Labs Backpack");
            WebElement addToCartButton = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
            addToCartButton.click();

            // Otvori korpu
            System.out.println("Navigating to shopping cart page...");
            WebElement cartIcon = driver.findElement(By.cssSelector("#shopping_cart_container .shopping_cart_link"));
            cartIcon.click();

            // Proveri da li se dodati proizvod pojavljuje u korpi
            System.out.println("Checking if product is in the cart: Sauce Labs Backpack");
            Assert.assertTrue(shoppingCartPage.isProductInCart("Sauce Labs Backpack"));

            // Otvori stranicu sa proizvodom u korpi
            System.out.println("Opening the product page in the cart...");

            // Koristi eksplicitno čekanje
            WebDriverWait wait = new WebDriverWait(driver, 10);
            WebElement productInCart = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".cart_item a")));
            productInCart.click();



        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Test execution failed: " + e.getMessage());
        }
    }
}


