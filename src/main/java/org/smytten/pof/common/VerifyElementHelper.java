package org.smytten.pof.common;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.smytten.pof.cart.CartPage;

import java.util.NoSuchElementException;

public class VerifyElementHelper {

   public static boolean isPopupPresent(AndroidDriver driver) {
        try {
            return PopUp.getPopUpClose(driver).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public static boolean isProductConsentPopupPresent(AndroidDriver driver) {
        try {
            return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/okButton")).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public static boolean isAutoApplyCouponPresent(AndroidDriver driver){
       try {
           return CartPage.getAnimationCoupon(driver).isDisplayed();
       }catch (Exception e){
           return false;
       }
    }

    public static boolean isCodConsentPopUpPresent(AndroidDriver driver){
        try {
            return PopUp.getCodPopUp(driver).isDisplayed();
        }catch (NoSuchElementException e){
            return false;
        }
    }

    public static boolean isPaymentConsentPopupPresent(AndroidDriver driver) {
       try {
           return PopUp.getCartConsentPopup(driver).isDisplayed();
       }catch (Exception e){
           return false;
       }
    }

    public static boolean isAppWidePopUpPresent(AndroidDriver driver) {
        try {
            return PopUp.getOfferPopUp(driver).isDisplayed();
        }catch (Exception e){
            return false;
        }
    }

    public static boolean isSignUpPopUpPresent(AndroidDriver driver){
        try {
            return PopUp.getSignUpPopup(driver).isDisplayed();
        }catch (Exception e){
            return false;
        }
    }

}
