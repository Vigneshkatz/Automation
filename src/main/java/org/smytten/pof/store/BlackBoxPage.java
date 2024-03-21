package org.smytten.pof.store;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;

public class BlackBoxPage {

    public static WebElement getLandingPageToolbarLayout(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/ll_toolbar"));
    }

    public static WebElement getLandingPageBackButton(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/backBtn"));
    }

    public static WebElement getLandingPageHeadingInfo(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/im_heading_info"));
    }

    public static WebElement getLandingPageHeadingBanner(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_heading_banner"));
    }

    public static WebElement getLandingPageProductBanner(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/im_product_banner"));
    }

    public static WebElement getLandingPageSubtitles(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/exo_subtitles"));
    }

    public static WebElement getLandingPageListLayout(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/rl_list"));
    }

    public static WebElement getLandingPageBrandDescription2(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_dec_brand2"));
    }

    public static WebElement getLandingPageBrandDescription(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_dec_brand"));
    }

    public static WebElement getLandingPagePlayPauseButton(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_play_pause"));
    }

    public static WebElement getLandingPageMuteButton(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_video_mute"));
    }

    public static WebElement getLandingPageCatalogueButton(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/btn_bf_catalogue"));
    }


    public static WebElement getListingPageHeaderLayout(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/cl_header"));
    }

    public static WebElement getListingPageBackgroundImage(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/img_one_bf_bg"));
    }

    public static WebElement getListingPageCartBannerImage(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/img_bf_cart_banner"));
    }

    public static WebElement getListingPageImageRecyclerView(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/rv_image"));
    }

    public static WebElement getListingPageRootLayout(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/cl_root"));
    }

    public static WebElement getListingPageAddToCartButton(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_add_cart"));
    }

    public static WebElement getListingPageProductName(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_product_name"));
    }

    public static WebElement getListingPageBrandName(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_brand_name"));
    }

    public static WebElement getListingPageProductImage(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/img_product_item"));
    }

    public static WebElement getListingPageFootersLayout(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/ll_footers"));
    }

    public static WebElement getListingPageReviewBuyText(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/review_buy_txt"));
    }

    public static WebElement getBlackBoxCartTitle(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_title"));
    }

    public static WebElement getBlackBoxCartSubTitle(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_sub_title"));
    }

    public static WebElement getBlackBoxCartMainRootLayout(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/cl_root_main"));
    }

    public static WebElement getBlackBoxCartCloseButton(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_close"));
    }

    public static WebElement getBlackBoxCartRootLayout(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/cl_root"));
    }

    public static WebElement getBlackBoxProceedCtaButton(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/btn_cta"));
    }

    public static WebElement getBlackBoxCartFinalPrice(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_final_price"));
    }

    public static WebElement getBlackBoxCartSummaryRecyclerView(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/rv_summary"));
    }

    public static WebElement getBlackBoxCartTotalText(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_total"));
    }
}
