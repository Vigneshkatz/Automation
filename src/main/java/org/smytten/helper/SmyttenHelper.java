package org.smytten.helper;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.clipboard.ClipboardContentType;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.smytten.pof.account.AccountPage;
import org.smytten.pof.account.AddressPage;
import org.smytten.pof.account.ProfileUpdatePage;
import org.smytten.pof.cart.CartPage;
import org.smytten.pof.cart.TrialOrderConfirmation;
import org.smytten.pof.common.Header;
import org.smytten.pof.common.Navigation;
import org.smytten.pof.common.PopUp;
import org.smytten.pof.common.VerifyElementHelper;
import org.smytten.pof.entry.LandingPage;
import org.smytten.pof.entry.LoginPage;
import org.smytten.pof.entry.OtpPage;
import org.smytten.pof.entry.SignUpPage;
import org.smytten.pof.listing.ShopListingPage;
import org.smytten.pof.payment.AllPaymentPage;
import org.smytten.pof.payment.PaymentPage;
import org.smytten.pof.payment.RazorpayPaymentStatusPage;
import org.smytten.pof.product.ShopProductCard;
import org.smytten.pof.rewards.RewardProductCard;
import org.smytten.pof.shopfront.ShopFrontPage;
import org.smytten.util.Utility;
import org.smytten.util.contants.Bank;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

import static org.testng.AssertJUnit.*;

public class SmyttenHelper {
    AndroidDriver driver;
    AndroidHelper androidHelper;

    public SmyttenHelper(AndroidDriver driver, AndroidHelper androidHelper) {
        this.androidHelper = androidHelper;
        this.driver = driver;
    }

    public void signOut() throws AssertionError, Exception {

        checkPopUp();
        gotoAccountPage();
        androidHelper.scrollToBottom();
        WebElement signOut = AccountPage.getSignOut(driver);
        assertNotNull("Sign out button not found", signOut);
        signOut.click();

        WebElement yesCta = driver.findElement(AppiumBy.id("android:id/button1"));
        assertNotNull("Yes button not found", yesCta);
        yesCta.click();

    }

    public void checkPopUp() throws AssertionError, Exception {
        try {
            if (VerifyElementHelper.isPopupPresent(driver)) {
                WebElement popUpClose = PopUp.getPopUpClose(driver);
                assertNotNull("Popup close button not found", popUpClose);
                popUpClose.click();
                assertTrue("Popup successfully closed", true);
            } else {
                assertTrue("No popUp present", true);
            }
        } catch (NoSuchElementException e) {
            System.out.println(" no popUp plz check");
            throw e;
        } catch (AssertionError | Exception e) {
            throw e;
        }
    }

    public void gotoAccountPage() throws AssertionError, Exception {
        WebElement profileHomeTab = Navigation.getProfileHomeTab(driver);
        assertNotNull("Profile home tab not found", profileHomeTab);
        profileHomeTab.click();
    }

    public void signUpHelper() throws AssertionError, Exception {
        WebElement maleElement = SignUpPage.getMaleGenderOption(driver);
        WebElement femaleElement = SignUpPage.getFemaleGenderOption(driver);
        WebElement chooseGender = (Utility.RANDOMNUMBER == 0) ? maleElement : femaleElement;
        chooseGender.click();
        SignUpPage.getMonthSpinner(driver).click();
        SignUpPage.getMarchMonthOption(driver).click();
        SignUpPage.getYearSpinner(driver).click();
        SignUpPage.getYear2009Option(driver).click();
        WebElement referralInput = SignUpPage.getReferralInput(driver);
        referralInput.click();
        referralInput.sendKeys(SignUpPage.GROUP_INVITE_CODE);
        SignUpPage.getReferralApplyBtn(driver).click();
        WebElement referralSuccessTitle = SignUpPage.getReferralSuccessTitle(driver);
        assertNotNull("Referral success title not found", referralSuccessTitle);
        System.out.println(referralSuccessTitle.getText());
        WebElement referralSuccessPaymentTitle = SignUpPage.getReferralSuccessPaymentTitle(driver);
        System.out.println(referralSuccessPaymentTitle.getText());
        SignUpPage.getConfirmBtn(driver).click();
    }

    public void gotoTrialFront() throws AssertionError, Exception {
        if (VerifyElementHelper.isAppWidePopUpPresent(driver)) {
            PopUp.getPopUpClose(driver).click();
        }
        WebElement trialFront = Navigation.getTrialHomeTab(driver);
        assertNotNull(trialFront);
        trialFront.click();
    }

    public void gotoCart() throws AssertionError, Exception {
        WebElement cart = Navigation.getCartView(driver);
        assertNotNull(cart);
        cart.click();
        checkAutoApplyCoupon();

    }

    public void openPaymentPage() throws AssertionError, Exception {
        WebElement proceedBtn = CartPage.getProceedButton(driver);
        assertNotNull(proceedBtn);
        proceedBtn.click();
        try {
            if (VerifyElementHelper.isPaymentConsentPopupPresent(driver)) {
                PopUp.getRightCtaConsentPopUp(driver).click();
            }
        } catch (Exception e) {
            System.out.println("line -> " + Utility.getCurrentLineNo() + " No consent popUp found");
        }

    }

    public void updateAddress() throws AssertionError, Exception {
        WebElement firstName = AddressPage.getFirstNameField(driver);
        WebElement lastName = AddressPage.getLastNameField(driver);
        WebElement phoneNumber = AddressPage.getMobileElement(driver);
        WebElement houseNumber = AddressPage.getHouseField(driver);
        WebElement streetName = AddressPage.getStreetField(driver);
        WebElement landmark = AddressPage.getLandmarkField(driver);
        WebElement email = AddressPage.getEmailField(driver);
        WebElement pincode = AddressPage.getPincodeField(driver);
        WebElement city = AddressPage.getCityField(driver);
        WebElement state = AddressPage.getStateField(driver);
        WebElement saveAddress = AddressPage.getProceedButton(driver);

        androidHelper.clearAndSetValueInField(firstName, Utility.generateRandomString(8));
        androidHelper.clearAndSetValueInField(lastName, Utility.generateRandomString(8));
        androidHelper.clearAndSetValueInField(phoneNumber, "9500752205 ");
        androidHelper.clearAndSetValueInField(email, Utility.generateRandomEmail());
        androidHelper.clearAndSetValueInField(houseNumber, Utility.generateRandomString(8));
        androidHelper.clearAndSetValueInField(streetName, Utility.generateRandomString(8));
        androidHelper.clearAndSetValueInField(pincode, AddressPage.PINCODE);
        androidHelper.clearAndSetValueInField(landmark, Utility.generateRandomString(8));

        Random random = new Random();
        int randomIndex = random.nextInt(AddressPage.ADDRESS_TYPE.length);
        String randomAddressType = AddressPage.ADDRESS_TYPE[randomIndex];
        WebElement addressType = AddressPage.getAddressFieldType(randomAddressType, driver);
        addressType.click();
        WebElement defaultAddress = AddressPage.getDefaultAddressCheckbox(driver);
        defaultAddress.click();
        assertEquals(city.getText().toLowerCase(), AddressPage.CITY.toLowerCase());
        assertEquals(state.getText().toLowerCase(), AddressPage.STATE.toLowerCase());
        saveAddress.click();
        Thread.sleep(2000);

    }

    public void signUp(boolean isSignup, boolean isAppOpen) throws AssertionError, Exception {
        if(isAppOpen){
            openLoginPage();
        }
        WebElement mobileInput = LoginPage.getMobileNumberInput(driver);
        assertNotNull("Mobile number input field not found", mobileInput);
        mobileInput.click();
        mobileInput.sendKeys(Utility.getNumber());

        WebElement proceedBtn = LoginPage.getSendOtpButton(driver);
        proceedBtn.click();
        Thread.sleep(5000);
        if (!(VerifyElementHelper.isSignUpPopUpPresent(driver))) {
            isSignup = false;
        }

        if (isSignup) {
            signUpHelper();
        } else {
            if (VerifyElementHelper.isAppWidePopUpPresent(driver)) {
                PopUp.getPopUpClose(driver).click();
            }
            signOut();
            signUp(true, false);
        }

    }

    public void initiateSignUp() throws AssertionError, Exception {
        openLoginPage();
        signUp(true, true);
    }

    public void initiateLogin(String mobileNumber, String otp) throws Exception {
        WebElement mobileInput = LoginPage.getMobileNumberInput(driver);
        assertNotNull("Mobile input field not found", mobileInput);
        mobileInput.click();
        androidHelper.clearAndSetValueInField(mobileInput, mobileNumber);
        WebElement proceedBtn = LoginPage.getSendOtpButton(driver);
        proceedBtn.click();

        WebElement otpContainer = OtpPage.getOtpContainer(driver);
        assertNotNull("OTP container not found", otpContainer);

        WebElement otpEnterInput = OtpPage.getOtpEnterInput(driver);
        otpEnterInput.click();
        androidHelper.enterValue(otp);
        System.out.println("OTP typed successfully." + OtpPage.VALID_OTP);
    }

    public void openLoginPage() throws AssertionError, Exception {
        LandingPage landingPage  = new LandingPage();
        WebElement startCta = landingPage.getStartCtaElement();
        assertNotNull(startCta);
        startCta.click();

    }

    public void openShopFront() throws AssertionError, Exception {
        WebElement shopHomeTab = Navigation.getShopHomeTab(driver);
        assertNotNull(shopHomeTab);
        shopHomeTab.click();
        checkPopUp();

    }

    public void checkAutoApplyCoupon() {
        try {
            if (VerifyElementHelper.isAutoApplyCouponPresent(driver)) {
                tap(877, 877);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void placeCodOrder() throws AssertionError, Exception {

        WebElement codOption = PaymentPage.getCodOption(driver);
        assertNotNull(codOption);
        codOption.click();
        PaymentPage.getProceedBtn(driver).click();
        try {
            if (VerifyElementHelper.isCodConsentPopUpPresent(driver)) {
                PopUp.getCodProceedBtn(driver).click();
            }
        } catch (Exception e) {
            System.out.println("line -> " + Utility.getCurrentLineNo() + " No consent popup");
        }

    }

    public void openOrderDetailPageFromOrderConfirmationPage() throws AssertionError, Exception {
        WebElement orderDetail = TrialOrderConfirmation.getViewOrderDetailButton(driver);
        orderDetail.click();
        assertNotNull(orderDetail);

    }

    public void copyOtp() {
        try {
            androidHelper.clearNotifications();
//            testLoginWithCorrectOTP();
            WebElement actionContainer = driver.findElement(AppiumBy.id("android:id/actions_container_layout"));
            assertNotNull("Action container not found", actionContainer);

            WebElement copyAction = driver.findElement(AppiumBy.xpath("//android.widget.Button[@content-desc=\"Copy Verification Code\"]"));
            copyAction.click();
            System.out.println("OTP copied successfully");

            String otp = driver.getClipboard(ClipboardContentType.PLAINTEXT);
            System.out.println("Copied OTP: " + otp);

            String decodedOtp = androidHelper.decodeOtp(otp);
            System.out.println("Decoded OTP: " + decodedOtp);

            androidHelper.clearNotifications();
        } catch (Exception e) {
            System.out.println("Error occurred: " + e.getMessage());
            fail("copyOtp failed");
        } finally {
            try {
                signOut();
            } catch (Exception e) {
                fail("signout failed" + e.getMessage());
            }
        }
    }

    public void updatePincode() throws AssertionError, Exception {
        WebElement pincodeInput = ProfileUpdatePage.getPincodeInput(driver);
        pincodeInput.click();
        androidHelper.clearAndSetValueInField(pincodeInput, "635115");
    }

    public void updateDOB() throws AssertionError, Exception {
        WebElement chooseMonth = ProfileUpdatePage.getBirthdateInput(driver);
        chooseMonth.click();
        WebElement selectMonth = ProfileUpdatePage.getSelectMarch(driver);
        selectMonth.click();

        WebElement chooseYear = ProfileUpdatePage.getChooseYear(driver);
        chooseYear.click();

        WebElement selectYear = ProfileUpdatePage.getSelectYear(driver);
        selectYear.click();
    }

    public void updateGender() throws AssertionError, Exception {

        WebElement otherGender = ProfileUpdatePage.getOthersOption(driver);
        otherGender.click();
    }

    public void updateEmail() throws AssertionError, Exception {
        String email = Utility.generateRandomEmail();
        WebElement emailInput = ProfileUpdatePage.getEmailInput(driver);
        emailInput.click();
        androidHelper.clearAndSetValueInField(emailInput, email);
    }

    public void updateName() throws AssertionError, Exception {
        String name = "Not Guest User";
        WebElement nameInput = ProfileUpdatePage.getNameInput(driver);
        androidHelper.clearAndSetValueInField(nameInput, name);
    }

    public void openShopFrontMenu(int menuId) throws AssertionError, Exception {

        List<WebElement> menuList = ShopFrontPage.getMenuList(driver);
        assertNotNull(menuList);
        if (menuId > menuList.size()) {
            throw new NoSuchElementException();
        }
        for (WebElement menu : menuList) {
            if (menuId == 0) {
                menu.click();
            }
            menuId--;
        }

    }

    public void addShopProductToCart(int numberOfProduct) throws AssertionError, Exception {
        List<WebElement> productCardList = ShopProductCard.getAllProductCard(driver);
//        assertNotNull(productCardList);
        if (numberOfProduct > productCardList.size()) {
            throw new NoSuchElementException();
        }
        for (WebElement productCard : productCardList) {
            if (numberOfProduct <= 0) {
                break;
            }
            scrollElementToCenter(productCard);
            ShopProductCard.addProductToCard(productCard);
            WebElement snackBar = Header.getSnackBar(driver);
            assertNotNull(snackBar);
            try {
                PopUp.getFreebieFrenzyPopUp(driver);
                Thread.sleep(5000);
            } catch (Exception e) {
                System.out.println("no popUp");
            }
            System.out.println(snackBar.getText());
            numberOfProduct--;
        }
    }

    public void changeShopSubcategory(int i) throws AssertionError, Exception {
        WebElement subCatergory = ShopListingPage.getSubCategory(driver);
        List<WebElement> subCatergoryList = ShopListingPage.getAllSubCategory(subCatergory);
        assertNotNull(subCatergoryList);
        for (WebElement subCategory : subCatergoryList) {
            if (i == 1) {
                subCategory.click();
                System.out.println(subCategory.getText());
            }
            i--;
        }
    }

    public void scrollElementToCenter(WebElement element) {
        Dimension size = driver.manage().window().getSize();
        int centerX = size.width / 2;
        int centerY = size.height / 2;
        tap(centerX, centerY);
    }

    public void updateUserProfile() throws AssertionError, Exception {
        updateName();
        updateEmail();
        updateGender();
        updateDOB();
        updatePincode();
        WebElement saveBtn = ProfileUpdatePage.getProceedButton(driver);
        saveBtn.click();
    }

    public void loginWithValidOTP(String mobileNumber, String otp) throws AssertionError, Exception {
        openLoginPage();
        initiateLogin(mobileNumber, otp);
    }

    public void addRewardProductToCart(int noOfProducts) throws AssertionError, Exception {
        List<WebElement> productList = RewardProductCard.getAllRProductCard(driver);
        assertNotNull(productList);
        for (WebElement product : productList) {
            if (noOfProducts <= 0) {
                return;
            }
            WebElement cartCta = RewardProductCard.getRProductAddToCartCta(product);
            assertNotNull(cartCta);
            cartCta.click();
            try {
                PopUp.getProductConsentOkButton(driver);
                PopUp.getProductConsentOkButton(driver).click();
            } catch (Exception e) {
                System.out.println("No pop-up found");
            }
            System.out.println("product added to cart " + RewardProductCard.getRProductName(product).getText());
            noOfProducts--;
        }
    }

    public void placeOrderViaNetBanking() {
        try {
            WebElement allOption = PaymentPage.getAllPaymentsOption(driver);
            assertNotNull(allOption);
            allOption.click();
            PaymentPage.getProceedBtn(driver).click();
            if (VerifyElementHelper.isPaymentConsentPopupPresent(driver)) {
                PopUp.getRightCtaConsentPopUp(driver).click();
            }
            Thread.sleep(5000);
            WebElement netBanking = AllPaymentPage.netBankingOption(driver);
            assertNotNull(netBanking);
            netBanking.click();
            WebElement bankChoice = AllPaymentPage.bankOption(driver, Bank.ICICI.getValue());
            assertNotNull(bankChoice);
            bankChoice.click();
            AllPaymentPage.proceedPaymentBtn(driver).click();
            Thread.sleep(5000);
            RazorpayPaymentStatusPage.getSuccessButton(driver).click();
            Thread.sleep(5000);
        } catch (AssertionError | Exception e) {
            fail("Net Banking failed" + e.getMessage());
        }
    }

    public void tap(int x, int y) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence tap = new Sequence(finger, 1);
        tap.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), x, y));
        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(List.of(tap));
    }
}
