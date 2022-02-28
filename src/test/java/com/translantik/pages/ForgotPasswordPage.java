package com.translantik.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ForgotPasswordPage extends BasePage{

    @FindBy(id = "prependedInput")
    public WebElement usernameOrEmail;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement requestButton;

    @FindBy(css = ".alert.alert-warn")
    public WebElement warningMessageForgotPassword;
}