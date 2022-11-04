package steps;

import com.context.Context;
import com.driver.Driver;

import com.utils.FileHelper;
import com.utils.Vars;
import com.utils.data.QueryHelper;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.log4j.Logger;

import org.json.JSONObject;
import org.openqa.selenium.Point;


public class InitSteps {
    final static Logger LOGGER = Logger.getLogger(InitSteps.class);
    @Before
    public void setUp(Scenario scenario) {

        var project = FileHelper.getPropertyFromFile("config.properties", "project");
        var baseUrl = FileHelper.getPropertyFromFile("config.properties", "baseUrl");
        var runId = FileHelper.getPropertyFromFile("config.properties", "runid");
        var scenarioId = FileHelper.getPropertyFromFile("config.properties", "scenarioid");
        LOGGER.info("Starting scenario: " + scenario.getName());

                var driver = Driver.getDriver("REMOTE");

                    driver.manage().window().setPosition(new Point(1300, 0));
                    driver.manage().window().maximize();

        Context.setDriver(driver);
        Context.setVars(new Vars());
        Context.getVars().setProject(project);
        Context.getVars().setBaseUrl(baseUrl);
        Context.getVars().setScenarioId(scenarioId);
        Context.getVars().setRunId(runId);
    }

    @After
    public void tearDown(Scenario scenario) {
        var resultObject = new JSONObject();
        var sc = scenario.getName();
        var status = (scenario.isFailed()) ? "failed" : "passed";
        var project = Context.getVars().getProject();

        QueryHelper.sendStep(status);
        Context.unload();

    }



}
