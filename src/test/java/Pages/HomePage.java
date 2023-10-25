package Pages;
import org.openqa.selenium.By;
import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BaseTest {

    @FindBy(css = ".inventory_details_name.large_size")
    private WebElement itemName;

    @FindBy(css = ".btn.btn_primary.btn_small.btn_inventory")
    private WebElement addToCartButton;

    @FindBy(css = ".shopping_cart_container")
    private WebElement cartIcon;

    @FindBy(css = ".inventory_item_name")
    private WebElement itemNameInCart;

    public HomePage() {
        PageFactory.initElements(driver, this);
    }
    public String getItemNameText() {
        return getItemName().getText();
    }
    // Metoda za dobijanje imena proizvoda u korpi
    public String getItemNameInCart() {
        WebElement itemInCart = driver.findElement(By.cssSelector(".inventory_item_name"));
        return itemInCart.getText();
    }

    public void clickAddToCartButton() {
        addToCartButton.click();
    }

    public boolean isItemAddedToCart() {
        // Ovo je samo primer, zamenite sa stvarnom logikom za proveru da li je proizvod dodat u korpu.
        return cartIcon.isDisplayed();
    }

    public boolean isItemInCart(String itemName) {
        // Ovo je samo primer, zamenite sa stvarnom logikom za proveru da li se odabrani proizvod pojavljuje u korpi.
        return itemNameInCart.getText().equals(itemName);
    }
    public WebElement getItemName() {
        return itemName;
    }
    public void openCart() {
        driver.findElement(By.cssSelector(".shopping_cart_container")).click();
    }


}