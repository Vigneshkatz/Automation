package signin;

import base.BaseTest;
import org.openqa.selenium.WebElement;
import org.smytten.pof.entry.LandingPage;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.*;

public class LandingPageTest extends BaseTest {
    @Test
    public void initialLandingPageText() throws InterruptedException {
        try {
            LandingPage landingPage = new LandingPage(driver);
            assertNotNull(landingPage);
            WebElement startCta = landingPage.getStartCtaElement(driver);
            assertNotNull(startCta);
            assertTrue(startCta.getText().equalsIgnoreCase(landingPage.getExpectedCtaText()));
            startCta.click();
            Thread.sleep(5000);
        } catch (Exception e) {
            fail("landing page" + e.getMessage());
        }
    }
}
