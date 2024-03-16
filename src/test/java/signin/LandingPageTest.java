package signin;

import base.BaseTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebElement;
import org.smytten.pof.entry.LandingPage;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;
import static org.testng.AssertJUnit.assertNotNull;

public class LandingPageTest extends BaseTest {


    @Test(priority = 1)
    public void initialLandingPageText() {
        try {
            WebElement startCta = LandingPage.getStartCtaElement(driver);
            assertNotNull(startCta);
            assertTrue(startCta.getText().equalsIgnoreCase(LandingPage.getExpectedCtaText()));
            startCta.click();
            recordResult("initialLandingPageText", "Pass");
        } catch (AssertionError e) {
            recordResult("initialLandingPageText", "Fail "+e.getMessage());
            fail("landing page assertion failed: " + e.getMessage());
        } catch (Exception e) {
            recordResult("initialLandingPageText", "Fail");
            fail("landing page failed: " + e.getMessage());
        }
    }

    @Test
    public void verifyLandingPage() {
        try {
            WebElement topBanner = LandingPage.getTopBanner(driver);
            assertNotNull(topBanner);
            WebElement rootElement = LandingPage.getrootContent(driver);
            assertNotNull(rootElement);
            WebElement startCta = LandingPage.getStartCtaElement(driver);
            assertNotNull(startCta);
            recordResult("verifyLandingPage", "Pass");
        } catch (AssertionError e) {
            recordResult("verifyLandingPage", "Fail "+e.getMessage());
            fail("verification assertion failed: " + e.getMessage());
        } catch (Exception e) {
            recordResult("verifyLandingPage", "Fail "+e.getMessage());
            fail("verification failed: " + e.getMessage());
        }
    }

    @AfterMethod
    public void recordTestResult(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        String status = result.isSuccess() ? "Pass" : "Fail";
        testResults.put(methodName, status);
    }


}
