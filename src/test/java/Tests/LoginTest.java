package Tests;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

// Test klasa koja nasleđuje BaseTest klasu
public class LoginTest extends BaseTest {

    // Pre svakog testa postavljamo početno stanje
    @BeforeMethod
    public void pageSetup() {
        driver.navigate().to("https://www.saucedemo.com/");
    }

    // Test za uspešnu prijavu sa validnim kredencijalima
    @Test
    public void UserCanLoginWithValidCredentials() {
        // Korisničko ime i lozinka
        String validUsername = "standard_user";
        String validPassword = "secret_sauce";

        // Korisnik se prijavljuje
        loginUser(validUsername, validPassword);

        // Provera da li je preusmeren na odgovarajuću stranicu
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
    }

    // Metoda koja se koristi za prijavu korisnika
    private void loginUser(String username, String password) {
        loginPage.inputUsername(username);
        loginPage.inputPassword(password);
        loginPage.clickLoginButton();
    }

    // Test kada korisnik ne može da se prijavi sa praznim korisničkim imenom
    @Test
    public void userCannotLoginWithEmptyUsername() {
        // Prazno korisničko ime i validna lozinka
        loginUser("", "secret_sauce");

        // Provera da li je ostao na istoj stranici
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");

        // Provera prikaza poruke o grešci
        Assert.assertTrue(loginPage.errorMessage.isDisplayed(), "Error message is not displayed.");

        // Provera tačnog teksta poruke o grešci
        Assert.assertEquals(loginPage.errorMessage.getText(), "Epic sadface: Username is required", "Incorrect error message.");
    }

    // Test kada korisnik ne može da se prijavi sa praznom lozinkom
    @Test
    public void userCannotLoginWithEmptyPassword() {
        // Validno korisničko ime i prazna lozinka
        loginUser("standard_user", "");

        // Provera da li je ostao na istoj stranici
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");

        // Provera prikaza poruke o grešci
        Assert.assertTrue(loginPage.errorMessage.isDisplayed(), "Error message is not displayed.");

        // Provera tačnog teksta poruke o grešci
        Assert.assertEquals(loginPage.errorMessage.getText(), "Epic sadface: Password is required", "Incorrect error message.");
    }

    // Test kada korisnik ne može da se prijavi sa neispravnim kredencijalima
    @Test
    public void userCannotLoginWithInvalidCredentials() {
        // Neispravno korisničko ime i lozinka
        loginUser("some user", "some password");

        // Provera da li je ostao na istoj stranici
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");

        // Provera prikaza poruke o grešci
        Assert.assertTrue(loginPage.errorMessage.isDisplayed(), "Error message is not displayed.");

        // Provera tačnog teksta poruke o grešci
        Assert.assertEquals(loginPage.errorMessage.getText(), "Epic sadface: Username and password do not match any user in this service", "Incorrect error message.");
    }
}