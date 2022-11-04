package steps;

import com.context.Context;
import io.cucumber.java.en.Given;

public class MyStepdefs {
    @Given("I am wisiting google")
    public void iAmWisitingGoogle() {
        Context.getDriver().get("https://google.com");
    }
}
