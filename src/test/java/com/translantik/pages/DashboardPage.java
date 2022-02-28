package com.translantik.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DashboardPage extends BasePage{

    @FindBy(xpath = "//ul[@class='breadcrumb']/li[1]")
    public WebElement breadcrumbFirstLine;

    @FindBy(xpath = "//ul[@class='breadcrumb']/li[2]")
    public WebElement breadcrumbSecondLine;

    @FindBy(id = "breadcrumb")
    public WebElement breadCrumb;

    public String getBreadcrumb(){
        String breadcrumb = breadcrumbFirstLine.getText() + breadcrumbSecondLine.getText();
        return breadcrumb;
    }





}