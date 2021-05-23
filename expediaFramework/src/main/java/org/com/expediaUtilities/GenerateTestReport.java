package org.com.expediaUtilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;


public class GenerateTestReport extends TestListenerAdapter{

	ExtentHtmlReporter extentHtmlReporter;
	ExtentReports extentReports;
	ExtentTest extentTest;
	
	public void onStart(ITestContext context) {
	
		String fileName = "Test Report-"+new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date())+".html";
		extentHtmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"\\Reports\\"+fileName);
		
		extentHtmlReporter.config().setDocumentTitle("Automation Testing Report for Expedia");
		extentHtmlReporter.config().setReportName("Expedia Testing Report");
		
		extentReports = new ExtentReports();
		extentReports.attachReporter(extentHtmlReporter);

	}
	
	public void	onTestSuccess(ITestResult tr) {
		
		extentTest = extentReports.createTest(tr.getName());
		extentTest.log(Status.PASS, MarkupHelper.createLabel(tr.getName(),ExtentColor.GREEN));
	}
	
	public void onTestSkipped(ITestResult tr) {
		
		extentTest = extentReports.createTest(tr.getName());
		extentTest.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREY));
	}

	public void onTestFailure(ITestResult tr) {

		extentTest = extentReports.createTest(tr.getName());
		extentTest.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));

		String screenshotPath = System.getProperty("user.dir")+"\\Screenshots\\"+tr.getName()+".png";

		File f = new File(screenshotPath); 	

		if(f.exists()) {
			try {
				extentTest.addScreenCaptureFromPath(screenshotPath);
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}	
	
	public void onFinish(ITestContext context) {
		extentReports.setSystemInfo("OS", GetSystemInfo.getOperatingSystem());
		extentReports.setSystemInfo("OS", GetSystemInfo.getOperatingSystemVersion());
		extentReports.setSystemInfo("OS", GetSystemInfo.getOperatingSystemArchitecture());
		
		ArrayList<String> browserDetails = GetSystemInfo.getBrowserDetails(TestSupport.driver); 
		extentReports.setSystemInfo("Browser", browserDetails.get(0));
		extentReports.setSystemInfo("Browser Version", browserDetails.get(1));
		extentReports.flush();
	}
	
}
