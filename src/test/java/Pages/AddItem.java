package Pages;
import Base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class AddItem extends BaseTest {

    public AddItem() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".btn.btn_primary.btn_small.btn_inventory")
    public WebElement addToCartButton;

    @FindBy(css = ".btn.btn_secondary.btn_inventory")
    public WebElement removeItem;

    public void clickOnAddToCartButton() {
        addToCartButton.click();
    }

    public void clickOnRemoveItemButton() {
        removeItem.click();
    }

    // Metoda za dodavanje određenog proizvoda u korpu
    public void addProductToCart(String productName) {
        // Pronađi element proizvoda na osnovu teksta
        List<WebElement> productElements = driver.findElements(By.xpath("//div[@class='inventory_item_name' and text()='" + productName + "']"));

        // Proveri postojanje elementa pre nego što pokušate pristupiti
        if (!productElements.isEmpty()) {
            // Pronađi dugme "Add to cart" povezano sa određenim proizvodom
            WebElement addToCartButton = productElements.get(0).findElement(By.xpath(".//ancestor::div[@class='inventory_item']//button[@class='btn_primary btn_small btn_inventory']"));

            // Klikni na dugme "Add to cart"
            addToCartButton.click();

            // Dodatna pauza - možete prilagoditi prema potrebi
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".shopping_cart_badge")));
        } else {
            // Element nije pronađen, možete dodati odgovarajući kod ili log poruku
            System.out.println("Element with text '" + productName + "' not found.");
        }
    }
    // Nova metoda za proveru da li se proizvod nalazi u korpi
    public boolean isProductInCart(String productName) {
        try {
            // Pronalazi element korpe
            WebElement cartIcon = driver.findElement(By.cssSelector("#shopping_cart_container .shopping_cart_link"));
            cartIcon.click();

            // Proverava da li se proizvod pojavljuje u korpi
            WebElement productInCart = driver.findElement(By.xpath("//div[@class='inventory_item_name' and text()='" + productName + "']"));
            return productInCart.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
