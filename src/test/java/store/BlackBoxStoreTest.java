package store;

import base.BaseTest;
import org.openqa.selenium.WebElement;
import org.smytten.pof.store.BlackBoxPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

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

    @Test
    public void openBlackBoxStore() {
        try {
            smyttenHelper.openShopFrontMenu(4);
        } catch (Exception e) {
            fail("Open Black Box failed" + e.getMessage());
        }
    }

    @Test
    public void verifyBlackBoxLandingPage() {
        WebElement backButton = BlackBoxPage.getLandingPageBackButton(driver);
        WebElement backCtaButton = BlackBoxPage.getBlackBoxProceedCtaButton(driver);
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
    }

    @Test
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

    }

    @Test
    public void addProductsToBlackBoxCart() {

    }

    @Test
    public void verifyBlackBoxCart() {

    }

    @Test
    public void placeOrder() {

    }
}
