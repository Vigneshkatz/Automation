package accountsection;

import base.BaseTest;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import org.smytten.naviation.AccountPageNavigation;
import org.smytten.pof.account.AccountPage;
import org.smytten.pof.entry.LoginPage;
import org.smytten.pof.entry.OtpPage;
import org.smytten.pof.luxe.LuxeLandingPage;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.*;

public class AccountRedirectionTest extends BaseTest {
    private final Boolean isEmpty = true;

    @Test(priority = 1)
    public void testLoginWithCorrectOTP() {
        try {
            smyttenHelper.loginWithValidOTP(LoginPage.getMOBILE_NUMBER(2), OtpPage.VALID_OTP);
        } catch (AssertionError | Exception e) {
            fail("loginWithCrtOTP assertion failed: " + e.getMessage());
        }
    }

    @Test(priority = 2)
    public void testRedirectionTOAccountPage() {
        try {
            smyttenHelper.checkPopUp();
            smyttenHelper.gotoAccountPage();
        } catch (AssertionError | Exception e) {
            fail("checkPopUp " + e.getMessage());
        }
    }

    @Test(priority = 3)
    public void gotoAddressPage() {
        try {
            smyttenHelper.gotoAccountPage();
            AccountPageNavigation.openAddressPage(driver);
        } catch (AssertionError | Exception e) {
            fail("gotoAddressPage" + "Saved address element not found." + e.getMessage());
        }
    }

    @Test(priority = 4)
    public void verifyAddressPage() {
        try {
            WebElement addressTitle = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_title"));
            WebElement addNewAddressCta = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/ll_add_new_address"));

            // Assert common elements
            assertEquals("Shipping Address", addressTitle.getText());
            assertEquals("Add New Address +", addNewAddressCta.getText());

            if (isEmpty) {
                WebElement noAddressWarning = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_error"));
                WebElement placeholderImage = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/img_msg_placeholder"));

                assertNotNull("Placeholder image is displayed", placeholderImage);
                assertEquals("sorry, you have no saved address.", noAddressWarning.getText().toLowerCase());
                Thread.sleep(2000);
            } else {
                Thread.sleep(1000);
                addNewAddressCta.click();
            }
        } catch (AssertionError | Exception e) {
            fail("verifyAddressPag" + "Address page elements not found. " + e.getMessage());
        } finally {
            androidHelper.back();
        }
    }

    @Test(priority = 5)
    public void openTrialHowTo() {
        try {
            smyttenHelper.gotoAccountPage();
            androidHelper.scrollToTop();
            AccountPageNavigation.openTrialHowTo(driver);
            WebElement headerSection = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_wallet_banner_top"));
            WebElement trialTitle = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_title"));
            WebElement trialPoint = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_trial"));
            WebElement trialVideo = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/exo_subtitles"));
            WebElement trialBanner = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_banner_view"));
            WebElement content = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/cl_root"));

            // Assertions
            assertEquals("6", trialPoint.getText());
            assertEquals("Trial Points", trialTitle.getText());
            assertNotNull(headerSection);
            assertNotNull(trialVideo);
            assertNotNull(trialBanner);
            assertNotNull(content);
        } catch (AssertionError | Exception e) {
            fail("openTrialHowTo assertion failed: " + e.getMessage());
        } finally {
            driver.navigate().back();
        }
    }

    @Test(priority = 6)
    public void openShopHowTo() {
        try {
            smyttenHelper.gotoAccountPage();

            androidHelper.scrollToTop();
            AccountPageNavigation.openShopHowTo(driver);

            WebElement headerSection = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_wallet_banner_top"));
            WebElement shopTitle = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_title"));
            WebElement walletBalance = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_wallet_banner_amt"));
            WebElement shopVideo = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/exo_subtitles"));
            WebElement shopBanner = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_banner_view"));
            WebElement content = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/cl_root"));

            // Assertions
            assertEquals("₹0", walletBalance.getText());
            assertEquals("Smytten Wallet", shopTitle.getText());
            assertNotNull(headerSection);
            assertNotNull(shopVideo);
            assertNotNull(shopBanner);
            assertNotNull(content);
        } catch (AssertionError | Exception e) {
            fail("openShopHowTo assertion failed: " + e.getMessage());
        } finally {
            driver.navigate().back();
        }
    }

    @Test(priority = 7)
    public void openRewardsHowTo() {
        try {
            smyttenHelper.gotoAccountPage();
            androidHelper.scrollToTop();

            AccountPageNavigation.openRewardsHowTo(driver);
            WebElement headerSection = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_wallet_banner_top"));
            WebElement rewardTitle = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_title"));
            WebElement walletBalance = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_wallet_banner_amt"));
            WebElement rewardVideo = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/exo_subtitles"));
            WebElement rewardBanner = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_banner_view"));
            WebElement content = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/cl_root"));

            // Assertions
            assertEquals("0", walletBalance.getText());
            assertEquals("Smytten Bucks", rewardTitle.getText());
            assertNotNull(headerSection);
            assertNotNull(rewardVideo);
            assertNotNull(rewardBanner);
            assertNotNull(content);
        } catch (AssertionError | Exception e) {
            fail("openRewardsHowTo assertion failed: " + e.getMessage());
        } finally {
            // Navigate back
            driver.navigate().back();
        }
    }

    @Test(priority = 8)
    public void myOrders() {
        try {
            smyttenHelper.gotoAccountPage();
            androidHelper.scrollToTop();
            AccountPageNavigation.openMyOrderPage(driver);
            WebElement orderTitle = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"My Orders\"]"));
            WebElement emptyTitle = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_empty_title"));
            WebElement emptyData = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_empty_data"));
            WebElement emptySubtitle = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_empty_desc"));

            assertEquals("My Orders", orderTitle.getText());
            assertEquals("Sorry, You do not have any orders!", emptyTitle.getText());
            assertEquals("Discover something new, place an order today!", emptySubtitle.getText());
            assertNotNull(emptyData);
        } catch (AssertionError | Exception e) {
            fail("myOrders assertion failed: " + e.getMessage());
        } finally {
            driver.navigate().back();
        }
    }

    @Test(priority = 9)
    public void openHelp() {
        try {
            smyttenHelper.gotoAccountPage();

            androidHelper.scrollToTop();
            AccountPageNavigation.openHelpSection(driver);

            WebElement contactTitle = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_bucks_title1"));
            WebElement supportDelivery = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_support_1"));
            WebElement supportPayment = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_support_2"));
            WebElement supportRefund = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_support_3"));
            WebElement needMoreHelp = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Need More Help?\"]"));
            WebElement orderQueries = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/include_order"));
            WebElement accountQueries = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/include_mail"));
            WebElement chatBot = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/include_whatsapp"));
            WebElement otpFooter = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_header_otp"));

            // Assertions
            assertEquals("Contact Us", contactTitle.getText());
            assertNotNull(supportDelivery);
            assertNotNull(supportPayment);
            assertNotNull(supportRefund);
            assertNotNull(accountQueries);
            assertNotNull(orderQueries);
            assertNotNull(chatBot);
            assertEquals("Need More Help?", needMoreHelp.getText());
            assertEquals("We will never ask you to share your bank details, OTP or any other sensitive information", otpFooter.getText());
        } catch (AssertionError | Exception e) {
            fail("openHelp assertion failed: " + e.getMessage());
        } finally {
            // Navigate back
            driver.navigate().back();
        }
    }

    @Test(priority = 10)
    public void openReferAndEarn() {
        try {
            smyttenHelper.gotoAccountPage();
            androidHelper.scrollToTop();
            assertNotNull(AccountPage.getReferBanner(driver));
        } catch (AssertionError | Exception e) {
            fail("openReferAndEarn assertion failed: " + e.getMessage());
        }
    }

    @Test(priority = 11)
    public void openReview() {
        try {
            smyttenHelper.gotoAccountPage();
            // Find elements
            AccountPageNavigation.openReviewSection(driver);


            WebElement reviewTitle = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_title"));
            assertEquals("Review Section title", "Feedbacks & Surveys", reviewTitle.getText());

            WebElement tabs = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_tab1"));
            assertEquals("trial section", "Trial", tabs.getText());

            WebElement emptyState = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_empty_data"));
            assertNotNull("Empty state is not null", emptyState);

            WebElement emptyText = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_empty_title"));
            assertEquals("Empty State", "You don't have any products with pending feedback. \n" + "\n" + "Find all your recently purchased products here for review.", emptyText.getText());
        } catch (AssertionError | Exception e) {
            fail("openReview assertion failed: " + e.getMessage());
        } finally {
            // Navigate back
            driver.navigate().back();
        }
    }

    @Test(priority = 12)
    public void openSurvey() {
        try {
            // Navigate to the Account page
            smyttenHelper.gotoAccountPage();

            // Find elements
            WebElement surveySection = AccountPage.getSurveySection(driver);
            surveySection.click();
            assertNotNull("Survey section is not null", surveySection);

            WebElement surveyTitle = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_title"));
            assertEquals("Survey Section title", "Feedbacks & Surveys", surveyTitle.getText());

            WebElement tabs = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_tab3"));
            assertEquals("Surveys section", "Surveys", tabs.getText());

            WebElement emptyState = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_empty_data"));
            assertNotNull("Empty state is not null", emptyState);

            WebElement emptyText = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_empty_title"));
            assertEquals("Empty State", "Unfortunately, we do not have a survey available for you right now.", emptyText.getText());
        } catch (AssertionError | Exception e) {
            fail("openSurvey assertion failed: " + e.getMessage());
        } finally {
            // Navigate back
            driver.navigate().back();
            driver.navigate().back();
        }
    }

    @Test(priority = 13)
    public void openSavedAddress() {
        try {
            // Get the saved address element and click on it
            WebElement savedAddress = AccountPage.getSavedAddress(driver);
            assertNotNull("Saved address element is not null", savedAddress);
            savedAddress.click();

            // Verify elements on the saved address page
            WebElement addressTitle = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_title"));
            WebElement noAddressWarning = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_error"));
            WebElement addNewAddressCta = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/ll_add_new_address"));
            WebElement placeholderImage = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/img_msg_placeholder"));

            // Assertions for element presence and text
            assertNotNull("Address title element is present", addressTitle);
            assertNotNull("No address warning element is present", noAddressWarning);
            assertNotNull("Add new address button element is present", addNewAddressCta);
            assertNotNull("Placeholder image element is present", placeholderImage);

            // Assertions for text content
            assertEquals("Address title is 'Shipping Address'", "Shipping Address", addressTitle.getText());
            assertEquals("No address warning is as expected", "Sorry, you have no saved address.", noAddressWarning.getText());
            assertEquals("Text of 'Add New Address +' button is as expected", "Add New Address +", addNewAddressCta.getText());

        } catch (AssertionError | Exception e) {
            fail("Assertion failed: " + e.getMessage());
        } finally {
            driver.navigate().back();
        }
    }

    @Test(priority = 14)
    public void openWishList() {
        try {
            // Navigate to the account page
            smyttenHelper.gotoAccountPage();

            // Scroll to the end of the page
            androidHelper.scrollToBottom();
            // Find and click on the wishlist element
            AccountPageNavigation.openWishlist(driver);

            // Find all required elements on the wishlist page
            WebElement title = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_title"));
            WebElement cart = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_cart"));
            WebElement emptyPlaceHolder = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_empty_data"));
            WebElement toggle = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/cv_tabs"));
            WebElement trialToggle = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Trial\"]"));
            WebElement emptyTitle = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_empty_title"));
            WebElement description = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_empty_desc"));
            WebElement exploreCta = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_start_exploring"));
            WebElement closeCta = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_close"));

            // Assertions for element presence and text
            assertNotNull("Wishlist title element is present", title);
            assertNotNull("Cart icon element is present", cart);
            assertNotNull("Empty placeholder image element is present", emptyPlaceHolder);
            assertNotNull("Toggle element is present", toggle);
            assertNotNull("Trial toggle element is present", trialToggle);
            assertNotNull("Empty title element is present", emptyTitle);
            assertNotNull("Description element is present", description);
            assertNotNull("Explore CTA element is present", exploreCta);
            assertNotNull("Close CTA element is present", closeCta);

            // Assertions for text content
            assertEquals("Wishlist Title is 'My Wishlist'", "My Wishlist", title.getText());
            assertEquals("Empty title text is as expected", "Your wishlist is currently empty!", emptyTitle.getText());
            assertEquals("Description text is as expected", "Tap ❤ \uFE0F on your favourite products to find them here.", description.getText());
            assertEquals("Explore CTA text is as expected", "Explore Now", exploreCta.getText());

        } catch (AssertionError | Exception e) {
            fail("Assertion failed: " + e.getMessage());
        } finally {
            driver.navigate().back();
        }
    }

    @Test(priority = 15)
    public void openSmyttenLuxe() {
        try {
            // Navigate to the account page
            smyttenHelper.gotoAccountPage();

            // Find and click on the Smytten Luxe element
            AccountPageNavigation.openLuxeEntry(driver);

            // Find the Luxe title element and get its text
            WebElement luxeTitle = LuxeLandingPage.getLuxeTitle(driver);
            String luxeTitleText = luxeTitle.getText().trim();

            // Assertions for Luxe title and Proceed button
            assertEquals("Luxe title is 'Smytten Luxe'", "Smytten Luxe", luxeTitleText);
            WebElement luxeProceed = LuxeLandingPage.getProceedButton(driver);
            assertNotNull("Luxe Proceed button is not null", luxeProceed);

        } catch (AssertionError | Exception e) {
            fail("Assertion failed: " + e.getMessage());
        } finally {
            driver.navigate().back();
        }
    }

    @Test(priority = 16)
    public void openSmyttenBlog() {
        try {
            // Navigate to the account page
            smyttenHelper.gotoAccountPage();

            // Scroll to the end of the page
            androidHelper.scrollToBottom();
            // Find and click on the Smytten Blog element
            WebElement smyttenBlog = AccountPage.getSmyttenBlog(driver);
            assertNotNull("Smytten Blog element is not null", smyttenBlog);
            smyttenBlog.click();

            // Find the Blog title element and verify its text
            WebElement smyttenBlogTitle = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_title"));
            assertEquals("Blog title is 'Blog'", "Blog", smyttenBlogTitle.getText());

            // Find and verify the close icon element
            WebElement closeIcon = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_close"));
            assertNotNull("Close icon element is not null", closeIcon);
            Thread.sleep(5000);

        } catch (AssertionError | Exception e) {
            fail("Assertion failed: " + e.getMessage());
        } finally {
            driver.navigate().back();
        }
    }

    @Test(priority = 18)
    public void openTermsAndCondition() {
        try {
            // Navigate to the account page
            smyttenHelper.gotoAccountPage();

            // Scroll to the end of the page
            androidHelper.scrollToBottom();
            // Find and click on the Terms and Condition element
            AccountPageNavigation.openTnC(driver);

            // Find the Terms and Condition title element and verify its text
            WebElement tncTitle = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_title"));
            assertEquals("Terms and Condition title is 'Terms'", "Terms", tncTitle.getText().trim());

            // Find and verify the close icon element
            WebElement closeIcon = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_close"));
            assertNotNull("Close icon element is not null", closeIcon);

        } catch (AssertionError | Exception e) {
            fail("Assertion failed: " + e.getMessage());
        } finally {
            driver.navigate().back();
        }
    }

    @Test(priority = 19)
    public void openPrivacyPolicy() {
        try {
            // Navigate to the account page
            smyttenHelper.gotoAccountPage();

            // Scroll to the end of the page
            androidHelper.scrollToBottom();
            // Find and click on the Privacy Policy element
            AccountPageNavigation.openPrivacyPolicy(driver);

            // Find the Privacy Policy title element and verify its text
            WebElement privacyTitle = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_title"));
            assertEquals("Privacy Policy title is 'Privacy Policy'", "Privacy Policy", privacyTitle.getText());

            // Find and verify the close icon element
            WebElement closeIcon = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_close"));
            assertNotNull("Close icon element is not null", closeIcon);

        } catch (AssertionError | Exception e) {
            fail("Assertion failed: " + e.getMessage());
        } finally {
            driver.navigate().back();
        }
    }

    @Test(priority = 20)
    public void openFaqs() {
        try {
            smyttenHelper.gotoAccountPage();
            androidHelper.scrollToBottom();
            WebElement faqs = AccountPage.getFaqs(driver);
            assertNotNull("FAQs element is not null", faqs);
            faqs.click();

            WebElement faqTitle = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_title"));
            assertEquals("FAQ title is 'FAQ'", "FAQ", faqTitle.getText());

            WebElement closeIcon = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_close"));
            assertNotNull("Close icon element is not null", closeIcon);
        } catch (AssertionError | Exception e) {
            fail("Assertion failed: " + e.getMessage());
        } finally {
            driver.navigate().back();
        }
    }

    @Test(priority = 21)
    public void testSignOut() {
        try {
            smyttenHelper.gotoAccountPage();
            androidHelper.scrollToBottom();
            WebElement signOut = AccountPage.getSignOut(driver);
            signOut.click();
            WebElement yesCta = driver.findElement(AppiumBy.id("android:id/button1"));
            yesCta.click();
        } catch (AssertionError | Exception e) {
            fail("signOut assertion failed: " + e.getMessage());
        }
    }
}
