package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BaseTest {


        public LoginPage() {
            PageFactory.initElements(driver, this);
        }
//anotacija
        @FindBy(id = "user-name")
        public WebElement usernameField;
        @FindBy(id = "password")
        public WebElement passwordField;
        @FindBy(id = "login-button")
        public WebElement loginButton;
        @FindBy(css = ".error-message-container.error")
        public WebElement errorMessage;


        public void inputUsername(String userName){
            usernameField.clear();
            usernameField.sendKeys(userName);
        }

        public void inputPassword(String password){
            passwordField.clear();
            passwordField.sendKeys(password);
        }


        public void clickLoginButton(){
            loginButton.click();
        }


    }