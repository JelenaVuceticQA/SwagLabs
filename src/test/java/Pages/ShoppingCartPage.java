package Pages;

import Base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class ShoppingCartPage extends BaseTest {

    @FindBy(css = ".cart_icon")
    public WebElement shoppingCartIcon;

    public ShoppingCartPage() {
        PageFactory.initElements(driver, this);
    }

    // Metoda za klik na ikonicu korpe
    public void clickOnShoppingCartIcon() {
        shoppingCartIcon.click();
    }

    public void navigateToShoppingCart() {
        driver.findElement(By.cssSelector("#shopping_cart_container .shopping_cart_link")).click();
    }

    // Metoda za proveru da li se određeni proizvod nalazi u korpi
    public boolean isProductInCart(String productName) {
        String xpath = "//div[@class='inventory_item_name' and contains(text(), '" + productName + "')]";
        WebElement productElement = driver.findElement(By.xpath(xpath));
        return productElement.isDisplayed();
    }

    // Metoda za dodavanje proizvoda u korpu

    public void addProductToCart(String productName) {
        // Klik na dugme "Add to cart" za određeni proizvod
        String buttonXpath = "//div[@class='inventory_item_name' and text()='" + productName +
                "']/following-sibling::button[@data-test='add-to-cart-" + productName.toLowerCase().replace(" ", "-") + "']";
        WebElement addToCartButton = driver.findElement(By.xpath(buttonXpath));
        addToCartButton.click();
    }
}