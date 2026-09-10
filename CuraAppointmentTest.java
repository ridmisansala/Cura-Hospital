import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CuraAppointmentTest {

    private WebDriver driver;
    private WebDriverWait wait;

    private final String BASE_URL = "https://katalon-demo-cura.herokuapp.com/";

    private final String USERNAME = "John Doe";
    private final String PASSWORD = "ThisIsNotAPassword";

    @BeforeMethod
    public void setUp() {
        /*driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));*/

        ChromeOptions options = new ChromeOptions();
        Map<String, Object> preferences = new HashMap<>();

        // Disable Chrome's password-saving service
        preferences.put("credentials_enable_service", false);

        // Disable the "Save password?" popup
        preferences.put("profile.password_manager_enabled", false);

        // Disable compromised/leaked-password warnings
        preferences.put("profile.password_manager_leak_detection", false);
        options.setExperimentalOption("prefs", preferences);

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
    }

    @Test
    public void bookAppointmentTest() {

        // Step 1: Open the CURA Healthcare Service website
        driver.get(BASE_URL);

        // Verify the home page
        WebElement homeHeading = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[normalize-space(.)=" + "'CURA Healthcare Service']")));
        Assert.assertTrue(homeHeading.isDisplayed(), "CURA Healthcare Service home page is not displayed.");
        System.out.println("PASS: Home page is displayed.");

        // Step 2: Click Make Appointment
        WebElement makeAppointmentButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("btn-make-appointment")));
        makeAppointmentButton.click();

        // Step 3: Assert the login page
        WebElement loginHeading = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[normalize-space(.)='Login']")));
        Assert.assertEquals(loginHeading.getText().trim(), "Login", "Login page heading is incorrect.");

        // Step 4: Log in with the existing credentials
        WebElement usernameField = driver.findElement(By.id("txt-username"));
        WebElement passwordField = driver.findElement(By.id("txt-password"));
        usernameField.clear();
        usernameField.sendKeys(USERNAME);
        passwordField.clear();
        passwordField.sendKeys(PASSWORD);
        driver.findElement(By.id("btn-login")).click();

        // Step 5: Assert the appointment page
        WebElement appointmentHeading = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[normalize-space(.)=" + "'Make Appointment']")));
        Assert.assertEquals(appointmentHeading.getText().trim(), "Make Appointment", "Make Appointment page is not displayed.");
        System.out.println("PASS: Make Appointment page is displayed.");

        // Step 6: Select the second dropdown value
        WebElement facilityDropdownElement = driver.findElement(By.id("combo_facility"));
        Select facilityDropdown = new Select(facilityDropdownElement);
        facilityDropdown.selectByIndex(1);
        String selectedFacility = facilityDropdown.getFirstSelectedOption().getText().trim();
        Assert.assertEquals(selectedFacility, "Hongkong CURA Healthcare Center", "The second facility was not selected.");
        System.out.println("Selected facility: " + selectedFacility);

        // Step 7: Select the hospital-readmission checkbox
        WebElement readmissionCheckbox = driver.findElement(By.name("hospital_readmission"));
        if (!readmissionCheckbox.isSelected()) {
            readmissionCheckbox.click();
        }
        Assert.assertTrue(readmissionCheckbox.isSelected(), "Hospital readmission checkbox was not selected.");
        System.out.println("PASS: Hospital readmission is selected.");

        // Step 8: Select the second radio-button value
        List<WebElement> healthcarePrograms = driver.findElements(By.cssSelector("input[name='programs']"));
        Assert.assertTrue(healthcarePrograms.size() >= 2, "At least two healthcare-program options " + "were expected.");
        WebElement secondRadioButton = healthcarePrograms.get(1);
        secondRadioButton.click();
        Assert.assertTrue(secondRadioButton.isSelected(), "The second healthcare program was not selected.");
        System.out.println("PASS: Medicaid healthcare program is selected.");

        // Step 9: Enter the visit date
        String visitDate = "25/09/2026";
        WebElement visitDateField = driver.findElement(By.id("txt_visit_date"));
        visitDateField.clear();
        visitDateField.sendKeys(visitDate);
        Assert.assertEquals(visitDateField.getAttribute("value"), visitDate, "Visit date was not entered correctly.");

        // Step 10: Enter a comment
        String comment = "Routine healthcare appointment.";
        WebElement commentField = driver.findElement(By.id("txt_comment"));
        commentField.clear();
        commentField.sendKeys(comment);

        // Step 11: Submit the appointment form
        WebElement bookAppointmentButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("btn-book-appointment")));
        bookAppointmentButton.click();

        // Step 12: Assert the success page
        WebElement confirmationHeading = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[normalize-space(.)=" + "'Appointment Confirmation']")));
        Assert.assertEquals(confirmationHeading.getText().trim(), "Appointment Confirmation", "Appointment confirmation page is not displayed.");
        System.out.println("PASS: Appointment Confirmation page is displayed.");

    }

    @AfterMethod
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}