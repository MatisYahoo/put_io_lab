package put.selenium.linear;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import put.selenium.utils.ScreenshotAndQuitOnFailureRule;

import static org.junit.Assert.assertEquals;


public class AccountsLinearScriptAT {

    private WebDriver driver;
    @Rule
    public ScreenshotAndQuitOnFailureRule screenshotOnFailureAndWebDriverQuitRule =
            new ScreenshotAndQuitOnFailureRule();


    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        this.driver = new ChromeDriver();
        screenshotOnFailureAndWebDriverQuitRule.setWebDriver(driver);
        driver.get("http://localhost:8080/accounts/controller?action=db_reset");
    }

    @Test
    public void successfulUserRegistration() throws Exception {
        driver.get("http://localhost:8080/accounts/controller?action=db_reset");
        driver.get("http://localhost:8080/accounts/controller");
        driver.findElement(By.linkText("Register")).click();
        driver.findElement(By.name("username")).clear();
        driver.findElement(By.name("username")).sendKeys("user1");
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys("password1");
        driver.findElement(By.name("repeat_password")).clear();
        driver.findElement(By.name("repeat_password")).sendKeys("password1");
        driver.findElement(By.name("name")).clear();
        driver.findElement(By.name("name")).sendKeys("My Name");
        driver.findElement(By.name("addressData")).clear();
        driver.findElement(By.name("addressData")).sendKeys("Poznan");
        driver.findElement(By.name("submit")).click();
        driver.findElement(By.name("username")).clear();
        driver.findElement(By.name("username")).sendKeys("user1");
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys("password1");
        driver.findElement(By.name("submit")).click();
        assertEquals("My Name, Poznan", driver.findElement(By.xpath("//div[@id='container']/div[2]")).getText());
        assertEquals("My Name, Poznan", driver.findElement(By.xpath("//div[@id='container']/div[2]")).getText());
    }


}
