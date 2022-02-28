package com.translantik.step_definitions;

import com.translantik.pages.DashboardPage;
import com.translantik.pages.ForgotPasswordPage;
import com.translantik.pages.LoginPage;
import com.translantik.utilities.BrowserUtils;
import com.translantik.utilities.ConfigurationReader;
import com.translantik.utilities.Driver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;

import java.util.ArrayList;
import java.util.List;

public class LoginStepDefinitions {

    LoginPage loginPage = new LoginPage();
    ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage();
    DashboardPage dashboardPage = new DashboardPage();
    String dashboardURL;
    WebElement activeElement;


    @Given("the user is on the the login page")
    public void theUserIsOnTheTheLoginPage() {
        Driver.get().get(ConfigurationReader.get("url"));
    }

    @When("the user enters {string} and {string}")
    public void the_user_enters_and(String username, String password) {
        loginPage.login(username, password);
    }

    @Then("the user should be able to login and {string} page should be displayed")
    public void the_user_should_be_able_to_login_and_page_should_be_displayed(String expectedTitle) {
        loginPage.waitUntilLoaderScreenDisappear();
        String actualTitle = loginPage.pageSubtitle.getText();
        System.out.println("actualTitle = " + actualTitle);
        Assert.assertEquals("Page Subtitle is NOT matched", expectedTitle, actualTitle);
    }

    @Given("the user logs in as a {string}")
    public void the_user_logs_in_as_a(String userType) {
        loginPage.loginAs(userType);
    }

    String currentUrl;

    @When("the user copy the URL after login, then logs out")
    public void the_user_copy_the_url_after_login_then_logs_out() {
        loginPage.waitUntilLoaderScreenDisappear();
        BrowserUtils.waitFor(3);
        currentUrl = Driver.get().getCurrentUrl();
        loginPage.userDropdownArrow.click();
        loginPage.logoutButton.click();
    }

    @And("the user try to navigate Dashboard page by providing the URL without providing credentials")
    public void theUserTryToNavigateDashboardPageByProvidingTheURLWithoutProvidingCredentials() {
        Driver.get().navigate().to(currentUrl);
        BrowserUtils.waitForPageToLoad(3);
    }

    @Then("the user should not be able to land on Dashboard page")
    public void the_user_should_not_be_able_to_land_on_dashboard_page() {
        String pageTitle = Driver.get().getTitle();
        System.out.println("pageTitle = " + pageTitle);
        Assert.assertFalse(pageTitle.equals("Dashboard"));
    }

    @When("the user enters a username with leading and trailing spaces {string} and valid password and clicks on login button")
    public void theUserEntersAUsernameWithLeadingAndTrailingSpacesAndValidPasswordAndClicksOnLoginButton(String username) {
        loginPage.usernameInput.sendKeys(username);
        loginPage.passwordInput.sendKeys(ConfigurationReader.get("password"));
        loginPage.loginButton.click();
    }

    @Then("username input box \\({string}) and password input box \\({string}) should have proper placeholders")
    public void usernameInputBoxAndPasswordInputBoxShouldHaveProperPlaceholders(String usernameHold, String passwordHold) {
        String placeholderOfUsername = loginPage.usernameInput.getAttribute("placeholder");
        String placeholderOfPassword = loginPage.passwordInput.getAttribute("placeholder");

        System.out.println(placeholderOfUsername);
        System.out.println(placeholderOfPassword);

        Assert.assertEquals("username input box has no proper placeholder", usernameHold, placeholderOfUsername);
        Assert.assertEquals("password input box has no proper placeholder", passwordHold, placeholderOfPassword);
    }

    @Then("{string} should be displayed")
    public void shouldBeDisplayed(String expectedWarningMessage) {
        String actualWarningMessage = loginPage.warningMessage.getText();
        System.out.println("actualWarningMessage = " + actualWarningMessage);
        Assert.assertEquals("Warning message NOT as expected", expectedWarningMessage, actualWarningMessage);

    }

    @Then("Warning message {string} should be displayed")
    public void warningMessageShouldBeDisplayed(String expectedMessage) {
        String actualMessage = "";
        if (loginPage.usernameInput.getAttribute("value").equals("")) {
            actualMessage = loginPage.usernameInput.getAttribute("validationMessage");
        }
        if(loginPage.passwordInput.getAttribute("value").equals("")) {
            actualMessage = loginPage.passwordInput.getAttribute("validationMessage");
        }
        Assert.assertEquals(expectedMessage,actualMessage);
    }

    @When("the user enters a password {string} into the password input box")
    public void theUserEntersAPasswordIntoThePasswordInputBox(String password) {
        loginPage.passwordInput.sendKeys(password);
    }

    @Then("the user should see password in a bullet sign format")
    public void theUserShouldSeePasswordInABulletSignFormat() {
        Assert.assertTrue(loginPage.passwordInput.getAttribute("type").equals("password"));
    }

    @Then("remember me on this computer links should be displayed and clickable")
    public void rememberMeOnThisComputerLinksShouldBeDisplayedAndClickable() {
        Assert.assertTrue(loginPage.rememberMeText.isDisplayed());
        Assert.assertTrue(loginPage.isClickable(loginPage.rememberMeCheckbox));
    }

    @When("the user clicks on Forgot your password? link")
    public void the_user_clicks_on_forgot_your_password_link() {
        loginPage.forgotPasswordButton.click();
    }

    @And("the user enters a username {string} into the input box and clicks on request button")
    public void theUserEntersAUsernameIntoTheInputBoxAndClicksOnRequestButton(String username) {
        forgotPasswordPage.usernameOrEmail.sendKeys(username);
        forgotPasswordPage.requestButton.click();
    }

    @Then("the user should be able to change password")
    public void theUserShouldBeAbleToChangePassword() {
        String unexpectedMessage = "Unable to send the email.";
        String actualMessage = forgotPasswordPage.warningMessageForgotPassword.getText();
        Assert.assertNotEquals("the user is NOT able to change password", unexpectedMessage, actualMessage);
    }

    @Then("Breadcrumb of Dashboard Page should be {string} displayed")
    public void breadcrumbOfDashboardPageShouldBeDisplayed(String expectedBreadcrumb) {
        String actualBreadcrumb = dashboardPage.breadCrumb.getText();
        System.out.println("actualBreadcrumb = " + actualBreadcrumb);
        Assert.assertEquals("Breadcrumb is NOT as expected", expectedBreadcrumb, actualBreadcrumb);
    }

    @Then("Page Heading of Dashboard Page should be {string} displayed")
    public void page_heading_of_dashboard_page_should_be_displayed(String expectedPageHeading) {
        String actualPageHeading = dashboardPage.pageSubtitle.getText();
        System.out.println("actualPageHeading = " + actualPageHeading);
        Assert.assertEquals("Page Heading is NOT as expected", expectedPageHeading, actualPageHeading);

    }

    @Then("Page URL of Dashboard Page should be {string} displayed")
    public void page_url_of_dashboard_page_should_be_displayed(String expectedURL) {
        String actualURL = Driver.get().getCurrentUrl();
        System.out.println("actualURL = " + actualURL);
        Assert.assertEquals("Page URL is NOT as expected", expectedURL, actualURL);
    }

    @Then("Page Title of Dashboard Page should be {string} displayed")
    public void page_title_of_dashboard_page_should_be_displayed(String expectedPageTitle) {
        String actualPageTitle = Driver.get().getTitle();
        System.out.println("actualPageTitle = " + actualPageTitle);
        Assert.assertEquals("Page Title is NOT as expected", expectedPageTitle, actualPageTitle);
    }

    @Then("Modules of Dashboard Page should be displayed as following")
    public void modules_of_dashboard_page_should_be_displayed_as_following(List<String> expectedModules) {
        List<String> actualModules = BrowserUtils.getElementsText(dashboardPage.modules);
        System.out.println("actualModules = " + actualModules);
        Assert.assertEquals("Modules are NOT as expected", expectedModules, actualModules);
    }

    @When("the user closes the browser without logging out and open browser again")
    public void theUserClosesTheBrowserWithoutLoggingOutAndOpenBrowserAgain() {
        dashboardURL = Driver.get().getCurrentUrl();
        Driver.closeDriver();
        Driver.get().get(dashboardURL);
    }

    @Then("verify the background color of Login button is \\(hex code {string})")
    public void verifyTheBackgroundColorOfLoginButtonIsHexCode(String expectedHex) {
        String colorRgba = loginPage.loginButton.getCssValue("background-color");
        String actualHex = Color.fromString(colorRgba).asHex();
        System.out.println("actualHex = " + actualHex);
        Assert.assertEquals(expectedHex, actualHex);
    }

    @Then("the user should not land on Dashboard page")
    public void theUserShouldNotLandOnDashboardPage() {
        String actualTitle = Driver.get().getTitle();
        String expectedTitle = "Dashboard";
        Assert.assertNotEquals(expectedTitle, actualTitle);
    }

    @And("the user copy the text entered into the password field and paste into the username input box")
    public void theUserCopyTheTextEnteredIntoThePasswordFieldAndPasteIntoTheUsernameInputBox() {

        /* in order to see what we have in clipboard

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Clipboard clipboard = toolkit.getSystemClipboard();
        String result = (String) clipboard.getData(DataFlavor.stringFlavor);
        */

        Actions actions = new Actions(Driver.get());
        actions.keyDown(Keys.COMMAND).sendKeys("a").keyUp(Keys.COMMAND).build().perform();
        actions.keyDown(Keys.COMMAND).sendKeys("c").keyUp(Keys.COMMAND).build().perform();
        BrowserUtils.waitFor(1);
        loginPage.usernameInput.sendKeys("");
        actions.keyDown(Keys.COMMAND).sendKeys("v").keyUp(Keys.COMMAND).build().perform();
        BrowserUtils.waitFor(1);

    }

    @When("the user enters a username {string} into the username input box")
    public void theUserEntersAUsernameIntoTheUsernameInputBox(String username) {
        loginPage.usernameInput.sendKeys(username);
    }

    @When("the user copy the URL and open a new TAB")
    public void theUserCopyTheURLAndOpenANewTAB() {
        dashboardURL = Driver.get().getCurrentUrl();
        ((JavascriptExecutor) Driver.get()).executeScript("window.open()");
        BrowserUtils.waitFor(1);
    }

    @And("the user closes the previous TAB and go to copied URL")
    public void theUserClosesThePreviousTABAndGoToCopiedURL() {
        ArrayList<String> tabs = new ArrayList<String>(Driver.get().getWindowHandles());
        Driver.get().close();
        Driver.get().switchTo().window(tabs.get(1));
        Driver.get().get(dashboardURL);
    }

    @Then("the user should see the Dashboard Page")
    public void theUserShouldSeeTheDashboardPage() {
        loginPage.waitUntilLoaderScreenDisappear();
        String expectedTitle = "Dashboard";
        String actualTitle = Driver.get().getTitle();
        Assert.assertEquals("User is NOT on dashboard page", expectedTitle, actualTitle);
    }

    @Then("password should not be visible in the page source")
    public void passwordShouldNotBeVisibleInThePageSource() {
        loginPage.passwordInput.sendKeys(ConfigurationReader.get("password"));
        String pageSource = Driver.get().getPageSource();
        System.out.println(pageSource);
        String password = ConfigurationReader.get("password");
        Assert.assertFalse(pageSource.contains(password));
    }

    @When("the user clicks on username input box, enter a valid username {string} and hit ENTER button")
    public void theUserClicksOnUsernameInputBoxEnterAValidUsernameAndHitENTERButton(String username) {
        activeElement = Driver.get().switchTo().activeElement();
        Assert.assertEquals("cursor is NOT on the username input box", activeElement, loginPage.usernameInput);
        loginPage.usernameInput.sendKeys(username + Keys.ENTER);
    }

    @And("the user enter a valid password {string}, hit ENTER button")
    public void theUserEnterAValidPasswordHitENTERButton(String password) {
        activeElement = Driver.get().switchTo().activeElement();
        Assert.assertEquals("Cursor is NOT on the password input box", activeElement, loginPage.passwordInput);
        loginPage.passwordInput.sendKeys(password + Keys.ENTER);
    }

    @When("the user clicks on username input box, enter a valid username {string} and hit TAB button")
    public void theUserClicksOnUsernameInputBoxEnterAValidUsernameAndHitTABButton(String username) {
        loginPage.usernameInput.sendKeys(username + Keys.TAB);
    }

    @Then("the text of username input should not match with entered password")
    public void theTextOfUsernameInputShouldNotMatchWithEnteredPassword() {
        String unExpectedText = "UserUser123";
        String actualText = loginPage.usernameInput.getAttribute("value");
        System.out.println(loginPage.passwordInput.getAttribute("value"));
        Assert.assertNotEquals(unExpectedText, actualText);
    }
}