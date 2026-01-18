package RakeshProject.Resources;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

	public static ExtentReports getReportObject() {
		String filePath = System.getProperty("user.dir") + "//reports//index.html";
//		String reportDir = System.getProperty("user.dir") + "//reports";
//		File dir = new File(reportDir);
//		if (!dir.exists()) {
//			dir.mkdirs();
//		}
//		
//		String filePath = reportDir + "//index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(filePath);
		reporter.config().setReportName("Ecommerce");
		reporter.config().setDocumentTitle("TestResults");
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Rakesh");
		return extent;

	}
}
