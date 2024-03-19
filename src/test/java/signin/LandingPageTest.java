package signin;

import base.BaseTest;
import org.openqa.selenium.WebElement;
import org.smytten.pof.entry.LandingPage;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class LandingPageTest extends BaseTest {

    @Test(priority = 1)
    public void testInitialLandingPageText() {
        try {
            WebElement startCta = LandingPage.getStartCtaElement(driver);
            assertNotNull(startCta);
            assertEquals(startCta.getText(), LandingPage.getExpectedCtaText(), "Start CTA text mismatch");
            startCta.click();
        } catch (AssertionError | Exception e) {
            fail("initialLandingPageText", e);
        }
    }

    @Test
    public void testVerifyLandingPageElements() {
        try {
            WebElement topBanner = LandingPage.getTopBanner(driver);
            assertNotNull(topBanner, "Top banner element not found");
            WebElement rootElement = LandingPage.getrootContent(driver);
            assertNotNull(rootElement, "Root content element not found");
            WebElement startCta = LandingPage.getStartCtaElement(driver);
            assertNotNull(startCta, "Start CTA element not found");
        } catch (AssertionError | Exception e) {
            fail("verifyLandingPage", e);
        }
    }
}
