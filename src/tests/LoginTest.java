package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.time.Duration;

public class LoginTest {
    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("start-maximized");
        options.addArguments("--disable-extensions");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36");

        driver = new ChromeDriver(options);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.get("https://www.ballarddesigns.com/");
    }

    @Test(priority = 1)
    public void verifyLogoIsDisplayed() {
        WebElement logo = driver.findElement(By.xpath("//div[@class='logo']"));
    }

    @Test(priority = 2)
    public void clickSignInRegister() {
        WebElement signInLink = driver.findElement(By.xpath("//a[contains(@class, 'c-link') and @href='/UserLogonView' and @title='Account']"));
        signInLink.click();
    }

    @Test(priority = 3)
    public void verifySignInPageText() {
 WebElement paragraph = driver.findElement(By.xpath("//p[text()='Welcome back! To access your account, please enter your email address and password and click Sign In.']"));
        String actualText = paragraph.getText();
        String expectedText = "Welcome back! To access your account, please enter your email address and password and click Sign In.";

        Assert.assertEquals(actualText, expectedText, "Sign in welcome text does not match!");       
    }

    @Test(priority = 4)
    public void enterCredentialsAndLogin() {
        WebElement emailField = driver.findElement(By.xpath("(//input[@id='email'])[1]")); 
        WebElement passwordField = driver.findElement(By.xpath("(//input[@id='password'])[1]"));

        emailField.sendKeys("testuser@example.com");
        passwordField.sendKeys("Test@123");

        WebElement loginButton = driver.findElement(By.xpath("(//span[normalize-space()='Sign In'])[1]"));
        loginButton.click();

    }

    @Test(priority = 5)
    public void verifyLoginSuccess() {
        WebElement myAccountLink = driver.findElement(By.xpath("//a[contains(@class, 'c-link') and @href='/AccountOverView' and @title='Account']"));
        myAccountLink.click();
    }

    @Test(priority = 6)
    public void verifyWelcomeMessage() {
 WebElement welcomeMessage = driver.findElement(By.xpath("//p[text()='Welcome to your account at Ballard Designs. ']"));
        String actualText = welcomeMessage.getText();
        String expectedText = "Welcome to your account at Ballard Designs." ;

        Assert.assertEquals(actualText, expectedText,  "Welcome message does not match!");       
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

