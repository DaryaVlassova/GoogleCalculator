package simpleEdit;

import java.awt.Robot;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import org.junit.Assert;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.AccountPage;
import pages.GmailHomePage;
import utilities.BrowserUtilities;
import utilities.ConfigurationReader;
import utilities.Driver;

public class Gmail {
	GmailHomePage home = new GmailHomePage();
	AccountPage account = new AccountPage();
	Actions actions = new Actions(Driver.getInstance());
	WebDriverWait wait = new WebDriverWait(Driver.getInstance(), 30);

	@Given("^I'm on gmail home page$")
	// My comment
	public void i_m_on_gmail_home_page() throws Throwable {
		Driver.getInstance().get(ConfigurationReader.getProperty("url2"));
	}

	@Given("^I enter valid \"([^\"]*)\" email$")
	public void i_enter_valid_email(String email) throws Throwable {
		home.email.sendKeys(email);

		home.nextButton.click();
		Thread.sleep(3000);
	}

	@Then("^I enter valid \"([^\"]*)\" password$")
	public void i_enter_valid_password(String password) throws Throwable {
		home.password.sendKeys(password);
		Thread.sleep(3000);

		// actions.moveToElement(home.password);
		// Thread.sleep(6000);
		// actions.sendKeys(password);
		// actions.build().perform();

	}

	@When("^I click on the login button$")
	public void i_click_on_the_login_button() throws Throwable {
		home.nextButton.click();
	}

	@Then("^the user name should be \"([^\"]*)\" name$")
	public void the_user_name_should_be_name(String name) throws Throwable {

		wait.until(ExpectedConditions.visibilityOf(account.nameTag));

		account.nameTag.click();
		String actualName = account.name.getText();
		System.out.println(actualName);
		Assert.assertEquals(name, actualName);

	}

	@Then("^I click on the napisat button$")
	public void i_click_on_the_napisat_button() throws Throwable {
		account.write.click();
	}

	@Then("^I enter addressee \"([^\"]*)\"$")
	public void i_enter_addressee(String arg1) throws Throwable {
		Thread.sleep(3000);
		account.adressee.sendKeys(arg1);
	}

	@Then("^I click on upload file$")
	public void i_click_on_upload_file() throws Throwable {
		BrowserUtilities.waitForPageLoad();
		account.attatchFile.click();
	}

	@Then("^I upload file$")
	public void i_upload_file() throws Throwable {
		StringSelection ss = new StringSelection("//Users//daryavlassova//Desktop//employee.xlsx");
		// copy to clipboard
		// Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss,
		// null);
		Robot robot = new Robot();
		// cmd+tab
		// robot.keyPress(KeyEvent.VK_META);
		// robot.keyPress(KeyEvent.VK_TAB);
		// robot.keyPress(KeyEvent.VK_META);
		// robot.keyRelease(KeyEvent.VK_TAB);
		// Thread.sleep(5000);

		// Go Window
		// cmd+shift+g

		robot.keyPress(KeyEvent.VK_META);
		robot.keyPress(KeyEvent.VK_SHIFT);
		robot.keyRelease(KeyEvent.VK_G);
		robot.keyPress(KeyEvent.VK_META);
		robot.keyPress(KeyEvent.VK_SHIFT);
		robot.keyRelease(KeyEvent.VK_G);

		// Paste the clibboard value
		robot.keyPress(KeyEvent.VK_META);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyPress(KeyEvent.VK_META);
		robot.keyRelease(KeyEvent.VK_V);
		// Hit Enter
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);

		Thread.sleep(2000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}

	@Then("^i click on send button$")
	public void i_click_on_send_button() throws Throwable {
		account.send.click();
	}
}
