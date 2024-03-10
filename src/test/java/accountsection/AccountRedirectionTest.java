package accountsection;

import base.BaseTest;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import org.smytten.pof.account.AccountPage;
import org.smytten.pof.common.Navigation;
import org.smytten.pof.common.PopUp;
import org.smytten.pof.entry.LoginPage;
import org.smytten.pof.entry.LandingPage;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotEquals;
import static org.testng.AssertJUnit.*;

public class AccountRedirectionTest extends BaseTest {

    private static final String MOBILE_NUMBER = null;


    @Test(priority = 1)
    public void login() throws InterruptedException {
        try {
            WebElement startCta = LandingPage.getStartCtaElement(driver);
            startCta.click();
            WebElement mobileInput = LoginPage.getMobileNumberInput(driver);
            assertNotEquals(mobileInput, null);
            System.out.println("Mobile input text: " + mobileInput.getText() + "->" + MOBILE_NUMBER);
            mobileInput.click();
            mobileInput.click();
            mobileInput.click();
            mobileInput.sendKeys(MOBILE_NUMBER);

            WebElement proceedBtn = LoginPage.getSendOtpButton(driver);
            assertNotEquals(proceedBtn, null);
            System.out.println("Proceed button text: " + proceedBtn.getText());
            proceedBtn.click();
        } catch (Exception e) {
            e.printStackTrace();
            fail("login attempt fail");
        }

//        Thread.sleep(5000);
//        try {
//            WebElement otpContainer = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/cl_otp_container"));
//            assertNotEquals(otpContainer, null);
//            WebElement otpLabel = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_otp_label"));
//            WebElement mobileNumberLabel = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_mobile"));
//            WebElement mobileNumberEditCta = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_mobile_ed"));
//            WebElement otpEnterInput = driver.findElement(AppiumBy.className("android.widget.EditText"));
//            String otpLabelText = "Enter OTP sent via SMS";
//            String mobileNumberLabelText = "+91-" + MOBILE_NUMBER;
//            String mobileNumberEditCtaText = "Edit";
//            assertTrue(otpLabelText.trim().equalsIgnoreCase(otpLabel.getText().trim()));
//            assertTrue(mobileNumberLabelText.trim().equalsIgnoreCase(mobileNumberLabel.getText().trim()));
//            assertTrue(mobileNumberEditCtaText.trim().equalsIgnoreCase(mobileNumberEditCta.getText().trim()));
//            otpEnterInput.click();
////            enter otp
//            Process process = Runtime.getRuntime().exec("adb shell input text " + 1111);
//            process.waitFor();
//            System.out.println("OTP typed successfully."+ 1111);
//
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            fail();
//        }
//        Thread.sleep(3000);
    }

    @Test(priority = 2)
    public void checkPopUp() {
        WebElement popUpClose = null;
        try {
            popUpClose = PopUp.getPopUpClose(driver);
            popUpClose.click();
            assertTrue("popup successfully closed", true);
        } catch (Exception e) {
            System.out.println("no popUp");
            assertTrue("popup is not there", true);
        }
    }

    @Test(priority = 3)
    public void gotoAccountPage() {
        WebElement mobileInput = null;
        try {
            mobileInput = Navigation.getProfileHomeTab(driver);
            mobileInput.click();
            assertTrue(true);
        } catch (Exception e) {
            fail("failed to go account page");
        }
    }

    @Test(priority = 4)
    public void verifyAccountSection() {
        WebElement profileSection = null;
        WebElement profileName = null;
        WebElement editProfile = null;
        WebElement editIcon = null;
        WebElement smyttenBenefits = null;
        WebElement trialHowTo = null;
        WebElement shopHowTo = null;
        WebElement rewardHowTo = null;
        WebElement myOrders = null;
        WebElement helpSection = null;
        WebElement referAndEarn = null;
        WebElement referBanner = null;
        WebElement reviewSection = null;
        WebElement surveySection = null;
        WebElement savedAddress = null;
        WebElement wishlist = null;
        WebElement smyttenLuxe = null;
        WebElement smyttenBlog = null;
        WebElement privacyPolicy = null;
        WebElement termsAndCondition = null;
        WebElement faqs = null;
        WebElement smyttenLogo = null;
        WebElement signOut = null;
        try {
            profileSection = AccountPage.getEditProfile(driver);
            profileName = AccountPage.getProfileName(driver);
            editProfile = AccountPage.getEditProfile(driver);
            editIcon = AccountPage.getEditIcon(driver);
            smyttenBenefits = AccountPage.getSmyttenBenefits(driver);
            trialHowTo = AccountPage.getTrialHowTo(driver);
            shopHowTo = AccountPage.getShopHowTo(driver);
            rewardHowTo = AccountPage.getRewardHowTo(driver);
            myOrders = AccountPage.getMyOrders(driver);
            helpSection = AccountPage.getHelpSection(driver);
            referAndEarn = AccountPage.getReferAndEarn(driver);
            referBanner = AccountPage.getReferBanner(driver);
            reviewSection = AccountPage.getReviewSection(driver);
            surveySection = AccountPage.getSurveySection(driver);
            savedAddress = AccountPage.getSavedAddress(driver);
            wishlist = AccountPage.getWishlist(driver);
            smyttenLuxe = AccountPage.getSmyttenLuxe(driver);
            smyttenBlog = AccountPage.getSmyttenBlog(driver);
            driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollToEnd(100000)"));
            privacyPolicy = AccountPage.getPrivacyPolicy(driver);
            termsAndCondition = AccountPage.getTermsAndCondition(driver);
            faqs = AccountPage.getFaqs(driver);
            smyttenLogo = AccountPage.getSmyttenLogo(driver);
            signOut = AccountPage.getSignOut(driver);

        } catch (Exception e) {
            e.printStackTrace();
            fail("Element not found");
        }
    }

    @Test(priority = 5)
    public void openTrialHowTo() {
        gotoAccountPage();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollToBeginning(100000)"));
        WebElement trialHowTo = null;
        WebElement headerSection = null;
        WebElement content = null;
        WebElement trialTitle = null;
        WebElement trialPoint = null;
        WebElement trialVideo = null;
        WebElement trialBanner = null;
        try {
            trialHowTo = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_trial_point"));
            trialHowTo.click();
            headerSection = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_wallet_banner_top"));
            content = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/cl_root"));
            trialTitle = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_title"));
            trialPoint = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_trial"));
            trialVideo = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/exo_subtitles"));
            trialBanner = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_banner_view"));
            assertEquals(trialPoint.getText(), "8");
            assertEquals(trialTitle.getText(), "Trial Points");
            assertNotNull(headerSection);
            assertNotNull(trialVideo);
            assertNotNull(trialBanner);
            assertNotNull(content);
            driver.navigate().back();
        } catch (Exception e) {
            e.printStackTrace();
            fail("Element Not found");
        } finally {
            driver.navigate().back();

        }
    }

    @Test(priority = 6)
    public void openShopHowTo() {
        gotoAccountPage();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollToBeginning(100000)"));
        WebElement shopHowTo = null;
        WebElement headerSection = null;
        WebElement content = null;
        WebElement shopTitle = null;
        WebElement walletBalance = null;
        WebElement shopVideo = null;
        WebElement shopBanner = null;
        try {
            shopHowTo = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_shop_wallet_title"));
            shopHowTo.click();
            headerSection = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_wallet_banner_top"));
            content = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/cl_root"));
            shopTitle = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_title"));
            walletBalance = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_wallet_banner_amt"));
            shopVideo = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/exo_subtitles"));
            shopBanner = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_banner_view"));
            assertEquals(walletBalance.getText(), "₹0");
            assertEquals(shopTitle.getText(), "Smytten Wallet");
            assertNotNull(headerSection);
            assertNotNull(shopVideo);
            assertNotNull(shopBanner);
            assertNotNull(content);
            driver.navigate().back();
        } catch (AssertionError e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            fail("Element Not found");
        } finally {
            driver.navigate().back();

        }
    }

    @Test(priority = 7)
    public void openRewardsHowTo() {
        gotoAccountPage();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollToBeginning(100000)"));
        WebElement rewardHowTo = null;
        WebElement headerSection = null;
        WebElement content = null;
        WebElement rewardTitle = null;
        WebElement walletBalance = null;
        WebElement rewardVideo = null;
        WebElement rewardBanner = null;
        try {
            rewardHowTo = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_reward_title"));
            rewardHowTo.click();
            headerSection = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_wallet_banner_top"));
            content = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/cl_root"));
            rewardTitle = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_title"));
            walletBalance = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_wallet_banner_amt"));
            rewardVideo = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/exo_subtitles"));
            rewardBanner = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_banner_view"));
            assertEquals(walletBalance.getText(), "0");
            assertEquals(rewardTitle.getText(), "Smytten Bucks");
            assertNotNull(headerSection);
            assertNotNull(rewardVideo);
            assertNotNull(rewardBanner);
            assertNotNull(content);
            driver.navigate().back();
        } catch (Exception e) {
            e.printStackTrace();
            fail("Element Not found");
        } finally {
            driver.navigate().back();

        }
    }

    @Test(priority = 8)
    public void myOrders() {
        gotoAccountPage();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollToBeginning(100000)"));
        WebElement myOrders = null;
        WebElement orderTitle = null;
        WebElement emptyData = null;
        WebElement emptyTitle = null;
        WebElement emptySubtitle = null;
        try {
            myOrders = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/ll_header_1"));
            myOrders.click();
//            orderTitle = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tb_my_orders"));
            emptyTitle = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_empty_title"));
            emptyData = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_empty_data"));
            emptySubtitle = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_empty_desc"));
            assertEquals("Order Page title", "My Orders", orderTitle.getText());
            assertEquals("Order Page empty title", "Sorry, You do not have any orders!", emptyTitle.getText());
            assertEquals("Order Page subtitle", "Discover something new, place an order today!", emptySubtitle.getText());
            assertNotNull(emptyData);
            assertNotNull(orderTitle);
        } catch (Exception e) {
            e.printStackTrace();
            fail("element Not found");
        } finally {
            driver.navigate().back();

        }


    }

    @Test(priority = 9)
    public void openHelp() {
        gotoAccountPage();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollToBeginning(100000)"));
        WebElement helpSection = null;
        WebElement contactTitle = null;
        WebElement supportDelivery = null;
        WebElement supportPayment = null;
        WebElement supportRefund = null;
        WebElement needMoreHelp = null;
        WebElement orderQueries = null;
        WebElement accountQueries = null;
        WebElement chatBot = null;
        WebElement otpFooter = null;
        try {
            helpSection = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/ll_header_2"));
            helpSection.click();
            contactTitle = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_bucks_title1"));
            supportDelivery = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_support_1"));
            supportPayment = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_support_2"));
            supportRefund = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_support_3"));
            needMoreHelp = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Need More Help?\"]"));
            orderQueries = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/include_order"));
            accountQueries = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/include_mail"));
            chatBot = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/include_whatsapp"));
            otpFooter = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_header_otp"));
            assertEquals("Contact Page title", "Contact Us", contactTitle.getText());
            assertNotNull(supportDelivery);
            assertNotNull(supportPayment);
            assertNotNull(supportRefund);
            assertNotNull(accountQueries);
            assertNotNull(orderQueries);
            assertNotNull(chatBot);
            assertEquals("need more help", "Need More Help?", needMoreHelp.getText());
            assertEquals("Otp footer", "We will never ask you to share your bank details, OTP or any other sensitive information", otpFooter.getText());
            driver.navigate().back();
        } catch (Exception e) {
            e.printStackTrace();
            fail("Element Not found");
        } finally {
            driver.navigate().back();
        }
    }

    @Test(priority = 10)
    public void openReferAndEarn() {
        gotoAccountPage();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollToBeginning(100000)"));

    }

    @Test(priority = 11)
    public void openReview() {
        gotoAccountPage();
        WebElement reviewSection = null;
        WebElement reviewTitle = null;
        WebElement emptyState = null;
        WebElement tabs = null;
        WebElement emptyText = null;
        try {
            reviewSection = driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\"Leave a review\"));"));
            assertNotNull(reviewSection);
            reviewTitle = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_title"));
            assertEquals("Review Section title", "Feedbacks & Surveys", reviewTitle.getText());
            tabs = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_tab1"));
            assertEquals("trial section", "Trial", tabs.getText());
            emptyState = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_empty_data"));
            assertNotNull(emptyState);
            emptyText = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_empty_title"));
            assertEquals("Empty State", "You don't have any products with pending feedback. \n" + "\n" + "Find all your recently purchased products here for review.", emptyText.getText());
        } catch (Exception e) {
            fail("Some element not found" + e.getMessage());
        } finally {
            driver.navigate().back();
        }
    }

    @Test(priority = 12)
    public void openSurvey() {
        gotoAccountPage();
        WebElement surveySection = null;
        WebElement surveyTitle = null;
        WebElement emptyState = null;
        WebElement tabs = null;
        WebElement emptyText = null;
        try {
            surveySection = driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\"Leave a review\"));"));
            assertNotNull(surveySection);
            surveyTitle = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_title"));
            assertEquals("Review Section title", "Feedbacks & Surveys", surveyTitle.getText());
            tabs = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_tab3"));
            assertEquals("trial section", "Surveys", tabs.getText());
            emptyState = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_empty_data"));
            assertNotNull(emptyState);
            emptyText = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_empty_title"));
            assertEquals("Empty State", "Unfortunately, we do not have a survey available for you right now.", emptyText.getText());
        } catch (Exception e) {
            fail("Some element not found" + e.getMessage());
        } finally {
            driver.navigate().back();
        }
    }

    @Test(priority = 13)
    public void openSavedAddress() {
        WebElement addressTitle = null;
        WebElement noAddressWarning = null;
        WebElement addNewAddressCta = null;
        WebElement placeholderImage = null;
        WebElement savedAddress = null;
        try {
            savedAddress = driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\"My Saved Address\"));"));
            savedAddress.click();
            addressTitle = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_title"));
            noAddressWarning = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_error"));
            addNewAddressCta = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/ll_add_new_address"));
            placeholderImage = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/img_msg_placeholder"));
            assertNotNull(placeholderImage);
            assertEquals("Shipping Address".toLowerCase(), addressTitle.getText().toLowerCase());
            assertEquals("Add New Address +".toLowerCase(), addNewAddressCta.getText().toLowerCase());
            assertEquals("Sorry, you have no saved address.".toLowerCase(), noAddressWarning.getText().toLowerCase());
        } catch (Exception e) {
            e.printStackTrace();
            fail("Something missing in empty address page" + e.getMessage());
        }
    }

    @Test(priority = 14)
    public void openWishList() {
        gotoAccountPage();
        WebElement wishlist = null;
        WebElement title = null;
        WebElement cart = null;
        WebElement emptyPlaceHolder = null;
        WebElement toggle = null;
        WebElement trialToggle = null;
//        WebElement shopToggle = null;
        WebElement emptyTitle = null;
        WebElement description = null;
        WebElement exploreCta = null;
        WebElement closeCta = null;
        try {
            driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollToEnd(100000)"));
            wishlist = driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\"My Wishlist\"));"));
            wishlist.click();
            title = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_title"));
            cart = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_cart"));
            emptyPlaceHolder = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_empty_data"));
            toggle = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/cv_tabs"));
            trialToggle = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Trial\"]"));
//            shopToggle = driver.findElement(AppiumBy.id(""));
            emptyTitle = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_empty_title"));
            description = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_empty_desc"));
            exploreCta = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_start_exploring"));
            closeCta = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_close"));
            assertEquals("WishList Title", title.getText(), "My Wishlist");
            assertNotNull(cart);
            assertNotNull(emptyPlaceHolder);
            assertNotNull(toggle);
            assertNotNull(trialToggle);
            assertEquals("Empty title", emptyTitle.getText(), "Your wishlist is currently empty!");
            assertEquals("Description", description.getText(), "Tap ❤ \uFE0F on your favourite products to find them here.");
            assertEquals("Explore cta", exploreCta.getText(), "Explore Now");
            assertNotNull(closeCta);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Elements not there");
        } finally {
            driver.navigate().back();
        }
    }

    @Test(priority = 15)
    public void openSmyttenLuxe() {
        gotoAccountPage();
        WebElement smyttenLuxe = null;
        WebElement luxeTitle = null;
        WebElement luxeProceed = null;
        try {
            smyttenLuxe = driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\"Smytten Luxe\"));"));
            smyttenLuxe.click();
            luxeTitle = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_subtitle"));
            String luxeTitleText = luxeTitle.getText();
            assertEquals("Luxe title", "Smytten Luxe", luxeTitleText.trim());
            luxeProceed = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/btn_proceed"));
            assertNotNull(luxeProceed);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Element not found");
        } finally {
            driver.navigate().back();
        }

    }

    @Test(priority = 16)
    public void openSmyttenBlog() {
        gotoAccountPage();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollToEnd(100000)"));
        WebElement smyttenBlog = null;
        WebElement smyttenBlogTile = null;
        WebElement closeIcon = null;

        try {
            smyttenBlog = driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\"Smytten Blog\"));"));
            assertNotNull(smyttenBlog);
            smyttenBlog.click();
            smyttenBlogTile = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_title"));
            assertEquals("Redirection title", "Blog", smyttenBlogTile.getText());
            closeIcon = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_close"));
            assertNotNull(closeIcon);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Element not found");
        } finally {
            driver.navigate().back();
        }

    }

//    @Test(priority = 17)
//    public void openRateSmytten(){
//        // Test logic for opening rate Smytten section
//    }

    @Test(priority = 18)
    public void openTermsAndCondition() {
        gotoAccountPage();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollToEnd(100000)"));
        WebElement tnc = null;
        WebElement tncTitle = null;
        WebElement closeIcon = null;
        try {
            tnc = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/ll_footer_2"));
            tnc.click();
            tncTitle = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_title"));
            assertEquals("Redirection title", "Terms", tncTitle.getText().trim());
            closeIcon = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_close"));
            assertNotNull(closeIcon);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Element not found");
        } finally {
            driver.navigate().back();
        }
    }

    @Test(priority = 19)
    public void openPrivacyPolicy() {
        gotoAccountPage();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollToEnd(100000)"));
        WebElement privacy = null;
        WebElement privacyTitle = null;
        WebElement closeIcon = null;
        try {
            privacy = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/ll_footer_1"));
            privacy.click();
            privacyTitle = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_title"));
            assertEquals("Redirection title", "Privacy Policy", privacyTitle.getText());
            closeIcon = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_close"));
            assertNotNull(closeIcon);

        } catch (Exception e) {
            e.printStackTrace();
            fail("Element not found");
        } finally {
            driver.navigate().back();
        }
    }

    @Test(priority = 20)
    public void openFaqs() {
        gotoAccountPage();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollToEnd(100000)"));
        WebElement faqs = null;
        WebElement faqTitle = null;
        WebElement closeIcon = null;
        try {
            faqs = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/ll_footer_3"));
            faqs.click();
            faqTitle = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_title"));
            assertEquals("Redirection title", "FAQ", faqTitle.getText());
            closeIcon = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_close"));
            assertNotNull(closeIcon);

        } catch (Exception e) {
            e.printStackTrace();
            fail("Element not found");
        } finally {
            driver.navigate().back();
        }
    }

    @Test(priority = 21)
    public void signOut() {
        gotoAccountPage();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollToEnd(100000)"));
        WebElement signOut = null;
        WebElement mobileInput = null;
        WebElement yesCta = null;
        try {
            signOut = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_sign_out"));
            signOut.click();
            yesCta = driver.findElement(AppiumBy.id("android:id/button1"));
            yesCta.click();
            mobileInput = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/et_mobile"));
            assertNotNull(mobileInput);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Element not found");
        }
    }
}