package entry;

import base.BaseTest;
import org.openqa.selenium.WebElement;
import org.smytten.pof.entry.LandingPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class LandingPageTest extends BaseTest {
    LandingPage landingPage;
    
    @BeforeClass
    public void initialSetUp(){
        landingPage = new LandingPage();
    }

    @Test(priority = 1)
    public void testInitiallandingPageText() {
        try {
            WebElement startCta = landingPage.getStartCtaElement();
            assertNotNull(startCta);
            assertTrue(landingPage.getExpectedCtaText().equalsIgnoreCase(startCta.getText()),"Start CTA text mismatch");
            startCta.click();
        } catch (AssertionError | Exception e) {
            fail("initiallandingPageText", e);
        }
    }

    @Test
    public void testVerifylandingPageElements() {
        try {
            WebElement topBanner = landingPage.getTopBanner();
            assertNotNull(topBanner, "Top banner element not found");
            WebElement rootElement = landingPage.getRootContent();
            assertNotNull(rootElement, "Root content element not found");
            WebElement startCta = landingPage.getStartCtaElement();
            assertNotNull(startCta, "Start CTA element not found");
        } catch (AssertionError | Exception e) {
            fail("verifylandingPage", e);
        }
    }
}
