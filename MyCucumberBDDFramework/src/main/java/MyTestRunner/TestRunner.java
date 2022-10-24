package MyTestRunner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions (
		features = "src\\main\\java\\Features\\Tags.feature", //the path of the feature files
		glue={"StepDefinitions"}, //the path of the step definition files
		plugin= {"pretty", "html:test-output.html"},
		dryRun = false,
		monochrome = true,
		tags = "@Smoke"
)


public class TestRunner {
	

	 
}
