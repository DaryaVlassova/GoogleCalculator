package simpleEdit;

import java.awt.Robot;
import java.awt.Toolkit;
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

		actions.moveToElement(home.password);
		actions.sendKeys(password);
		actions.build().perform();

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
		StringSelection ss = new StringSelection(
				"/Users/daryavlassova/Desktop/161201161116-83-year-in-pictures-2016-restricted-super-169.jpg");

		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

		// imitate mouse events like ENTER, CTRL+C, CTRL+V
		Robot robot = new Robot();
		robot.delay(250);

		robot.keyPress(KeyEvent.VK_RIGHT);
		robot.keyPress(KeyEvent.VK_RIGHT);
		robot.keyPress(KeyEvent.VK_RIGHT);
		robot.keyRelease(KeyEvent.VK_ENTER);

	}

	@Then("^i click on send button$")
	public void i_click_on_send_button() throws Throwable {

	}
}
