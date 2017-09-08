package runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features", glue = "simpleEdit", tags = "@Smoke", dryRun = false)
public class CukesRunner {
	// added a comment
}
