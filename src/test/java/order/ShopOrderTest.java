package order;

import base.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.NoSuchElementException;

import static org.testng.AssertJUnit.fail;

public class ShopOrderTest extends BaseTest {

    @BeforeClass
    public void initialSetup() {
        try {
            smyttenHelper.initiateSignUp();
            smyttenHelper.checkPopUp();
            smyttenHelper.openShopFront();
        } catch (Exception e) {
            fail("initial ShopOrderTest setup failed" + e.getMessage());
        }
    }

    @Test(priority = 0)
    public void testOpenShopListing() {
        try {
            smyttenHelper.openShopFrontMenu(3);
        } catch (AssertionError | NoSuchElementException e) {
            fail("testOpenShopFront is null" + e.getMessage());
        } catch (Exception e) {
            fail("something went wrong" + e.getMessage());
        }
    }

    @Test(priority = 1)
    public void testAddProductToCart() {
        try {
            smyttenHelper.changeShopSubcategory(3);
            smyttenHelper.addShopProductToCart(2);
        } catch (AssertionError e) {
            fail("Something went wrong while adding product to cart" + e.getMessage());
        } catch (NoSuchElementException e) {
            fail("Listing is empty" + e.getMessage());
        } catch (Exception e) {
            fail("something went wrong" + e.getMessage());
        }
    }

    @Test(priority = 2)
    public void testOpenCart() {
        try {
            smyttenHelper.gotoCart();
            smyttenHelper.openPaymentPage();
            smyttenHelper.updateAddress();
        } catch (Exception e) {
            fail("Open cart failed" + e.getMessage());
        }
    }

    @Test(priority = 3)
    public void testPlaceOrder() {
        try {
            smyttenHelper.placeCodOrder();
        } catch (Exception e) {
            fail("place order failed" + e.getMessage());
        }
    }

    @Test(priority = 4)
    public void testOrderConfirmation() {
        try {
            smyttenHelper.openOrderDetailPageFromOrderConfirmationPage();
        } catch (Exception e) {
            fail("Order Confirmation failed" + e.getMessage());
        }
    }

}
