package RetryFailedCase;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryListener implements IRetryAnalyzer {

	int failedCount=0;
	int limit=4;
	public boolean retry(ITestResult result) {
		if(failedCount<limit) {
			failedCount++;
			return true;
		}
		return false;
	}

}
