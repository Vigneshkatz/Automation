package org.smytten.pof.account;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class ProfileUpdatePage {

    private static WebElement closeButton;
    private static WebElement scrollContainer;
    private static WebElement formLayout;
    private static WebElement titleText;
    private static WebElement editPictureView;
    private static WebElement editProfileIcon;
    private static WebElement nameInput;
    private static WebElement emailInput;
    private static WebElement WebInput;
    private static WebElement maleOption;
    private static WebElement femaleOption;
    private static WebElement othersOption;
    private static WebElement birthdateInput;
    private static WebElement birthYearInput;
    private static WebElement pincodeInput;
    private static WebElement proceedButton;
    private static WebElement chooseMonth;
    private static WebElement selectMarch;
    private static WebElement chooseYear;
    private static WebElement selectYear;
    private final AndroidDriver driver;

    public ProfileUpdatePage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), ProfileUpdatePage.class);
    }

    public static WebElement getCloseButton(AndroidDriver driver) {
        return closeButton = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_close"));
    }

    public static WebElement getScrollContainer(AndroidDriver driver) {
        return scrollContainer = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/ns_scroll"));
    }

    public static WebElement getFormLayout(AndroidDriver driver) {
        return formLayout = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/ll_form"));
    }

    public static WebElement getTitleText(AndroidDriver driver) {
        return titleText = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_title"));
    }

    public static WebElement getEditPictureView(AndroidDriver driver) {
        return editPictureView = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/v_edit_pic"));
    }

    public static WebElement getEditProfileIcon(AndroidDriver driver) {
        return editProfileIcon = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_edit_profile"));
    }

    public static WebElement getNameInput(AndroidDriver driver) {
        return nameInput = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/et_name"));
    }

    public static WebElement getEmailInput(AndroidDriver driver) {
        return emailInput = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/et_registered_email"));
    }

    public static WebElement getWebInput(AndroidDriver driver) {
        return WebInput = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/et_Web"));
    }

    public static WebElement getMaleOption(AndroidDriver driver) {
        return maleOption = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_male"));
    }

    public static WebElement getFemaleOption(AndroidDriver driver) {
        return femaleOption = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_female"));
    }

    public static WebElement getOthersOption(AndroidDriver driver) {
        return othersOption = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_others"));
    }

    public static WebElement getBirthdateInput(AndroidDriver driver) {
        return birthdateInput = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/et_birthdate"));
    }

    public static WebElement getBirthYearInput(AndroidDriver driver) {
        return birthYearInput = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/et_birthyear"));
    }

    public static WebElement getPincodeInput(AndroidDriver driver) {
        return pincodeInput = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/et_pincode"));
    }

    public static WebElement getProceedButton(AndroidDriver driver) {
        return proceedButton = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/btn_proceed"));
    }

    public static WebElement getChooseMonth(AndroidDriver driver) {
        return chooseMonth = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/month_spinner"));
    }

    public static WebElement getSelectMarch(AndroidDriver driver) {
        return selectMarch = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@resource-id='android:id/title' and @text='March']"));
    }

    public static WebElement getChooseYear(AndroidDriver driver) {
        return chooseYear = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/year_spinner"));
    }

    public static WebElement getSelectYear(AndroidDriver driver) {
        return selectYear = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@resource-id='android:id/title' and @text='2009']"));
    }
}

