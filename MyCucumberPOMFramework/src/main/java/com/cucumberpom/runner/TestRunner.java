package com.cucumberpom.runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src\\main\\java\\com\\cucumber\\features\\Application.feature", //the path of the feature files
		glue={"com\\cucumber\\stepdefinitions"}, //the path of the step definition files
				plugin= {"pretty", "html:test-output.html"},
		dryRun = false,
		monochrome = true
		//tags = "@Smoke"
		)

public class TestRunner {

}
