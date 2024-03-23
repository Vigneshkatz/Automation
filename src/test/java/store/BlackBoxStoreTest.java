package store;

import base.BaseTest;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import org.smytten.pof.common.Header;
import org.smytten.pof.payment.PaymentPage;
import org.smytten.pof.store.BlackBoxPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.fail;

public class BlackBoxStoreTest extends BaseTest {
    @BeforeClass
    public void blackBoxStoreTestInitialSetup() {
        try {
            smyttenHelper.initiateSignUp();
            smyttenHelper.checkPopUp();
            smyttenHelper.openShopFront();
        } catch (Exception e) {
            fail("initial ShopOrderTest setup failed" + e.getMessage());
        }
    }

    @Test(priority = 0)
    public void openBlackBoxStore() {
        try {
            smyttenHelper.openShopFrontMenu(4);
        } catch (Exception e) {
            fail("Open Black Box failed" + e.getMessage());
        }
    }

    @Test(priority = 1)
    public void verifyBlackBoxLandingPage() {
        WebElement backButton = BlackBoxPage.getLandingPageBackButton(driver);
        WebElement brandDescription = BlackBoxPage.getLandingPageBrandDescription(driver);
        WebElement brandDescription2 = BlackBoxPage.getLandingPageBrandDescription2(driver);
        WebElement pageSubtitles = BlackBoxPage.getLandingPageSubtitles(driver);
        WebElement catalogueButton = BlackBoxPage.getLandingPageCatalogueButton(driver);
        WebElement headingBanner = BlackBoxPage.getLandingPageHeadingBanner(driver);
        WebElement landingPageHeadingInfo = BlackBoxPage.getLandingPageHeadingInfo(driver);
        WebElement listLayout = BlackBoxPage.getLandingPageListLayout(driver);
        WebElement muteButton = BlackBoxPage.getLandingPageMuteButton(driver);
        WebElement pauseButton = BlackBoxPage.getLandingPagePlayPauseButton(driver);
        WebElement pageProductBanner = BlackBoxPage.getLandingPageProductBanner(driver);
        WebElement toolbarLayout = BlackBoxPage.getLandingPageToolbarLayout(driver);
        assertNotNull(backButton);
        assertNotNull(brandDescription);
        assertNotNull(brandDescription2);
        assertNotNull(pageSubtitles);
        assertNotNull(catalogueButton);
        assertNotNull(headingBanner);
        assertNotNull(landingPageHeadingInfo);
        assertNotNull(listLayout);
        assertNotNull(muteButton);
        assertNotNull(pageProductBanner);
        assertNotNull(toolbarLayout);
        assertNotNull(pauseButton);
        catalogueButton.click();
    }

    @Test(priority = 2)
    public void verifyBlackBoxListingPage() {
        WebElement addToCartButton = BlackBoxPage.getListingPageAddToCartButton(driver);
        WebElement brandName = BlackBoxPage.getListingPageBrandName(driver);
        WebElement backgroundImage = BlackBoxPage.getListingPageBackgroundImage(driver);
        WebElement footersLayout = BlackBoxPage.getListingPageFootersLayout(driver);
        WebElement headerLayout = BlackBoxPage.getListingPageHeaderLayout(driver);
        WebElement cartBannerImage = BlackBoxPage.getListingPageCartBannerImage(driver);
        WebElement imageRecyclerView = BlackBoxPage.getListingPageImageRecyclerView(driver);
        WebElement productImage = BlackBoxPage.getListingPageProductImage(driver);
        WebElement rootLayout = BlackBoxPage.getListingPageRootLayout(driver);
        WebElement productName = BlackBoxPage.getListingPageProductName(driver);
        assertNotNull(addToCartButton);
        assertNotNull(backgroundImage);
        assertNotNull(brandName);
        assertNotNull(footersLayout);
        assertNotNull(headerLayout);
        assertNotNull(cartBannerImage);
        assertNotNull(imageRecyclerView);
        assertNotNull(productName);
        assertNotNull(productImage);
        assertNotNull(rootLayout);
    }

    @Test(priority = 3)
    public void addProductsToBlackBoxCart() {
        int maxCount = 3;
        List<WebElement> productCardList = BlackBoxPage.getListingPageRootLayoutList(driver);
        assertNotNull(productCardList);
        if(productCardList.size() < maxCount){
            fail("less products than cart count");
        }
        for(WebElement productCard : productCardList){
            if (maxCount == 0){
                break;
            }
           WebElement addToCart = productCard.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_add_cart"));
           addToCart.click();
           maxCount--;
        }
    }

    @Test(priority = 4)
    public void verifyBlackBoxCart() {
        WebElement goToCartBtn = BlackBoxPage.getGoToCart(driver);
        assertNotNull(goToCartBtn);
        goToCartBtn.click();
        WebElement cartPopUp = BlackBoxPage.getBlackBoxCartMainRootLayout(driver);
        assertNotNull(cartPopUp);

        List<WebElement> productList = cartPopUp.findElements(AppiumBy.id("com.app.smytten.debug:id/cl_root"));
        System.out.println(productList.size());
        WebElement goToPaymentPage = BlackBoxPage.getProceedToPaymentPage(driver);
        assertNotNull(goToPaymentPage);
       goToPaymentPage.click();
    }

    @Test(priority = 5)
    public void placeOrder() {
        try {
            smyttenHelper.updateAddress();
            smyttenHelper.placeCodOrder();
            Thread.sleep(10000);
        }catch (AssertionError | Exception e){
            fail("failed to placeOrder");
        }
    }
}
