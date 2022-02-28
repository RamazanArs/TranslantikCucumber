package com.translantik.pages;

import com.translantik.utilities.BrowserUtils;
import com.translantik.utilities.ConfigurationReader;
import com.translantik.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {

    @FindBy(id = "prependedInput")
    public WebElement usernameInput;

    @FindBy(id = "prependedInput2")
    public WebElement passwordInput;

    @FindBy(id = "_submit")
    public WebElement loginButton;

    @FindBy(css = ".alert.alert-error")
    public WebElement warningMessage;

    @FindBy(xpath = "//div[@class='form-signin__footer control-group form-row']/a")
    public WebElement forgotPasswordButton;

    @FindBy(css = ".custom-checkbox__icon")
    public WebElement rememberMeCheckbox;

    @FindBy(css = ".custom-checkbox__text")
    public WebElement rememberMeText;



    public void login(String username,String password){
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        loginButton.click();
    }

    public void loginAs(String userType){
        userType = userType.toLowerCase().replace(" ", "");
        String username = userType+"_username";
        usernameInput.sendKeys(ConfigurationReader.get(username));
        passwordInput.sendKeys(ConfigurationReader.get("password"));
        loginButton.click();
    }




}