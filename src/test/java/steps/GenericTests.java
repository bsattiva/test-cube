package steps;

import com.context.Context;
import com.page.Page;
import com.utils.FileHelper;
import com.utils.TestHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.testng.Assert;

public class GenericTests {



    @Given("I open {string}")
    public void iOpen(String page) {
        Context.getVars().setCurrentStepId("0");
        var url = Page.getPageUrl(page);
        Context.getDriver().get(url);
        var pageObject = new Page(page);

        Context.getVars().getPages().add(pageObject);
        FileHelper.appendPageToFile(pageObject.getPageObject());

    }

    @When("I populate {string} field with {string}")
    public void iPopulateFieldWith(String field, String value) {
        Context.getVars().setCurrentStepId("1");
        var element = (Context.getVars().getCurrentPage().getPageObject().getJSONObject(field));
        var searchField =  Page.getWebElement(TestHelper.getLocator(element));
        searchField.sendKeys(value);

    }

    @And("I click on {string}")
    public void iClickOn(String field) {
        Context.getVars().setCurrentStepId("2");
        var element = (Context.getVars().getCurrentPage().getPageObject().getJSONObject(field));
        var searchField =  Page.getWebElement(TestHelper.getLocator(element));
        searchField.click();
    }

    @Then("field {string} is populated with {string}")
    public void fieldIsPopulatedWith(String field, String value) {
        Context.getVars().setCurrentStepId("3");
        var element = (Context.getVars().getCurrentPage().getPageObject().getJSONObject(field));
        var searchField =  Page.getWebElement(TestHelper.getLocator(element));
        Assert.assertEquals(searchField.getText(), value);
    }
}
