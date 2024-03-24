package order;

import base.BaseTest;
import org.checkerframework.checker.units.qual.A;
import org.smytten.naviation.RewardsNavigation;
import org.smytten.naviation.SmyttenNavigation;
import org.smytten.pof.entry.LoginPage;
import org.smytten.pof.entry.OtpPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.fail;

public class RewardOrderTest extends BaseTest {
    @BeforeClass
    public void rewardsOrderTestSetUp(){
        try {
            smyttenHelper.openLoginPage();
            smyttenHelper.initiateLogin(LoginPage.getMOBILE_NUMBER(0),"1234");
            smyttenHelper.checkPopUp();
        }catch (AssertionError |Exception e){
            fail("rewardsOrderTestSetUp failed"+e.getMessage());
        }
    }
    @Test(priority = 0)
    public void openRewardListingPage(){
        try {
            SmyttenNavigation.goToRewardFront(driver);
            RewardsNavigation.openRewardListing(driver);
        }catch (Exception e){
            fail("Open Reward Front failed"+e.getMessage());
        }
    }

    @Test(priority = 1)
    public void addProductToCart(){
        try {
            smyttenHelper.addRewardProductToCart(1);
        }catch (Exception e){
            fail("Failed to add Product to cart"+e.getMessage());
        }
    }
    @Test(priority = 2)
    public void openRewardCart(){
        try {
            smyttenHelper.gotoCart();
            smyttenHelper.openPaymentPage();
        }catch (Exception e){
            fail("Failed to go to cart"+e.getMessage());
        }
    }
    @Test(priority = 3)
    public void placeOrder(){
        try {
            smyttenHelper.placeOrderViaNetBanking();
        }catch (Exception e){
            fail("failed to place Order"+e.getMessage());
        }
    }

}
