package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.Driver;

public class CalculatorPage {
	public CalculatorPage() {
		PageFactory.initElements(Driver.getInstance(), this);
	}

	@FindBy(id = "lst-ib")
	public WebElement googleSearch;
	@FindBy(css = "div#cwtltblr")
	public WebElement calculatorInput;

	@FindBy(css = "div.cwbtpl.cwdgb-tpl.cwsbts")
	public WebElement clear;
	@FindBy(xpath = "//div[@class='cwbtpl cwdgb-tpl cwbbts']//span[.='+']")
	public WebElement plus;
	@FindBy(xpath = "//div[@id='cwbt36']//span[@class='cwbts']")
	public WebElement minus;

	@FindBy(xpath = "//div[@class='cwbtpl cwdgb-tpl cwbbts']//span[.='×']")
	public WebElement multiplication;
	@FindBy(xpath = "//div[@class='cwbtpl cwdgb-tpl cwbbts']/span[@class='cwbts']")
	// "((//span[@class='cwbts'])[16]")
	public WebElement division;
	@FindBy(css = "div.cwbtpl.cwbb-tpl.cwbbts")
	public WebElement equals;
}
