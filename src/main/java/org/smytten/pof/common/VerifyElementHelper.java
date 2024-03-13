package org.smytten.pof.common;

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

    public static boolean isConsentPopupPresent(AndroidDriver driver) {
        try {
            return PopUp.getConsentPopup(driver).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public static boolean isAutoApplyCouponPresent(AndroidDriver driver){
       try {
           return CartPage.getAnimationCoupon(driver).isDisplayed();
       }catch (NoSuchElementException e){
           return false;
       }
    }

    public static boolean isCodCouponPopUpPresent(AndroidDriver driver){
        try {
            return PopUp.getCodPopUp(driver).isDisplayed();
        }catch (NoSuchElementException e){
            return false;
        }
    }

}
