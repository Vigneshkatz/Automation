package org.smytten.pof.listing;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ShopListingPage {
    public static WebElement getFilterButton(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_filter"));
    }

    public static WebElement getSearchButton(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/ll_search"));
    }

    public static WebElement getSearchInput(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/et_search"));
    }

    public static WebElement getFilterType(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/ll_filter_type"));
    }

    public static WebElement getTypeList(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_type_list"));
    }

    public static WebElement getTypeGrid(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_type_list"));
    }

    public static WebElement getSubCategory(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/rv_subcategory"));
    }

    public static List<WebElement> getAllSubCategory(WebElement subCategoryRow) {
        return subCategoryRow.findElements(AppiumBy.id("com.app.smytten.debug:id/cp_subcategory"));
    }
}
