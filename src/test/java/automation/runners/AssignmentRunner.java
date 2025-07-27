package automation.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(

        features = "src/test/java/automation/resources/features",
        glue = {"automation.stepdefinitions", "automation.hooks"},
        plugin = {"pretty", "html:target/cucumber-reports.html", "json:target/cucumber.json"},
        monochrome = true,
        tags = "@Assignment"
)
public class AssignmentRunner extends AbstractTestNGCucumberTests {

    }
