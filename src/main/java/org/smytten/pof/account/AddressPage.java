package org.smytten.pof.account;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.smytten.util.Utility;

public class AddressPage {

    public static final String[] ADDRESS_TYPE = {"Office", "Home", "Other"};
    public static final String FIRST_NAME = Utility.generateRandomString(8);
    public static final String LAST_NAME = Utility.generateRandomString(6);
    public static final String HOUSE_NO = Utility.generateRandomString(15);
    public static final String STREET = Utility.generateRandomString(15);
    public static final String LANDMARK = Utility.generateRandomString(15);
    public static final String PINCODE = "635115";
    public static final String STATE = "TAMIL NADU";
    public static final String CITY = "RAMAPURAM";
    private static WebElement firstNameField;
    private static WebElement lastNameField;
    private static WebElement webField;
    private static WebElement emailField;
    private static WebElement houseField;
    private static WebElement streetField;
    private static WebElement landmarkField;
    private static WebElement pincodeField;
    private static WebElement cityField;
    private static WebElement stateField;
    private static WebElement homeAddressButton;
    private static WebElement officeAddressButton;
    private static WebElement otherAddressButton;
    private static WebElement defaultAddressCheckbox;
    private static WebElement proceedButton;
    private static WebElement addNewAddressLayout;
    private static WebElement errorMessage;
    private static WebElement messagePlaceholderImage;
    private static WebElement titleText;
    private static WebElement addressRecyclerView;
    private static WebElement setDefaultButton;
    private static WebElement homeDefaultTextView;
    private static WebElement addressLayout;
    private static WebElement mobileElement;
    private final AndroidDriver driver;

    public AddressPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), AddressPage.class);
    }

    public static WebElement getAddressFieldType(String randomAddressType, AndroidDriver driver) {
        String locator = "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\"" + randomAddressType + "\"));";
        return driver.findElement(AppiumBy.androidUIAutomator(locator));
    }

    public static WebElement getMobileElement(AndroidDriver driver) {
        return mobileElement = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/et_mobile"));
    }

    public static WebElement getFirstNameField(AndroidDriver driver) {
        return firstNameField = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/et_fname"));
    }

    public static WebElement getLastNameField(AndroidDriver driver) {
        return lastNameField = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/et_lname"));
    }

    public static WebElement getWebField(AndroidDriver driver) {
        return webField = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/et_Web"));
    }

    public static WebElement getEmailField(AndroidDriver driver) {
        return emailField = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/et_email"));
    }

    public static WebElement getHouseField(AndroidDriver driver) {
        return houseField = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/et_house"));
    }

    public static WebElement getStreetField(AndroidDriver driver) {
        return streetField = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/et_street"));
    }

    public static WebElement getLandmarkField(AndroidDriver driver) {
        return landmarkField = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/et_landmark"));
    }

    public static WebElement getPincodeField(AndroidDriver driver) {
        return pincodeField = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/et_pincode"));
    }

    public static WebElement getCityField(AndroidDriver driver) {
        return cityField = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/et_city"));
    }

    public static WebElement getStateField(AndroidDriver driver) {
        return stateField = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/et_state"));
    }

    public static WebElement getHomeAddressButton(AndroidDriver driver) {
        return homeAddressButton = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/btn_home"));
    }

    public static WebElement getOfficeAddressButton(AndroidDriver driver) {
        return officeAddressButton = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/btn_office"));
    }

    public static WebElement getOtherAddressButton(AndroidDriver driver) {
        return otherAddressButton = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/btn_other"));
    }

    public static WebElement getDefaultAddressCheckbox(AndroidDriver driver) {
        return defaultAddressCheckbox = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/cb_default_address"));
    }

    public static WebElement getProceedButton(AndroidDriver driver) {
        return proceedButton = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/btn_proceed"));
    }

    public static WebElement getAddNewAddressLayout(AndroidDriver driver) {
        return addNewAddressLayout = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/ll_add_new_address"));
    }

    public static WebElement getErrorMessage(AndroidDriver driver) {
        return errorMessage = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_error"));
    }

    public static WebElement getMessagePlaceholderImage(AndroidDriver driver) {
        return messagePlaceholderImage = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/img_msg_placeholder"));
    }

    public static WebElement getTitleText(AndroidDriver driver) {
        return titleText = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_title"));
    }

    public static WebElement getAddressRecyclerView(AndroidDriver driver) {
        return addressRecyclerView = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/rv_address"));
    }

    public static WebElement getSetDefaultButton(AndroidDriver driver) {
        return setDefaultButton = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/btn_set_default"));
    }

    public static WebElement getSetCheckBoxDefaultButton(AndroidDriver driver) {
        return setDefaultButton = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/cb_default_address"));
    }


    public static WebElement getHomeDefaultTextView(AndroidDriver driver) {
        return homeDefaultTextView = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_home_default"));
    }

    public static WebElement getAddressLayout(AndroidDriver driver) {
        return addressLayout = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/ll_address_layout"));
    }
}
