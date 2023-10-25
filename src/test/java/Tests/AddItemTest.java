package Tests;


import Base.BaseTest;
import Pages.AddItem;
import Pages.HomePage;
import Pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddItemTest extends BaseTest {

    private LoginPage loginPage;
    private AddItem addItem;
    private HomePage homePage;

    @BeforeMethod
    public void pageSetUp() {
        System.out.println("Setting up the page...");

        loginPage = new LoginPage();
        addItem = new AddItem();
        homePage = new HomePage();

        driver.manage().window().maximize();
        System.out.println("Opening the home page: " + homeURL);
        driver.get(homeURL);
    }

    @Test(priority = 10)
    public void userCanAddRandomProductToCartAndRemove() {
        try {
            loginPage.inputUsername("standard_user");
            loginPage.inputPassword("secret_sauce");
            loginPage.clickLoginButton();

            // Idi na stranicu sa proizvodima
            System.out.println("Navigating to inventory page...");
            driver.get("https://www.saucedemo.com/inventory.html");

            // Izaberi proizvod "Sauce Labs Backpack" i dodaj ga u korpu
            System.out.println("Adding product to the cart: Sauce Labs Backpack");
            addItem.addProductToCart("Sauce Labs Backpack");

            // Pauza - eksplicitno čekanje nakon dodavanja proizvoda u korpu
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".shopping_cart_badge")));

            // Proveri da li je proizvod zaista dodat u korpu
            Assert.assertTrue(isProductInCart("Sauce Labs Backpack"));

            // Ukloni proizvod iz korpe
            System.out.println("Removing product from the cart: Sauce Labs Backpack");
            addItem.clickOnRemoveItemButton();

            // Pauza - eksplicitno čekanje nakon uklanjanja proizvoda iz korpe
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".shopping_cart_badge")));

            // Proveri da li je proizvod zaista uklonjen iz korpe
            Assert.assertFalse(isProductInCart("Sauce Labs Backpack"));

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Test execution failed: " + e.getMessage());
        }
    }

    // Dodatna metoda za proveru da li je proizvod u korpi
    private boolean isProductInCart(String productName) {
        try {
            // Idi na stranicu sa korpi
            driver.findElement(By.cssSelector("#shopping_cart_container .shopping_cart_link")).click();

            // Proveri da li se dodati proizvod pojavljuje u korpi
            WebElement productInCart = driver.findElement(By.xpath("//div[@class='cart_item' and .//div[@class='inventory_item_name' and text()='" + productName + "']]"));

            // Ako pronađen element, proizvod je u korpi
            return true;
        } catch (Exception e) {
            // Ako ne pronađen element, proizvod nije u korpi
            return false;
        }
    }
}