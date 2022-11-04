package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;


@CucumberOptions(plugin = {"pretty", "html:target/cucumber",
        "json:target/surefire-reports/cucumber-report.json",
        "listener.Listener"},
        features = {"src/test/features/"},
        glue = {"steps"},
        tags = "@default"
)

public class TestRunner extends AbstractTestNGCucumberTests {

}
