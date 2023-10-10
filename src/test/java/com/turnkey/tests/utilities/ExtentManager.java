package com.turnkey.tests.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.IOException;

public class ExtentManager {

    public static ExtentSparkReporter htmlReporter;
    public static ExtentReports extent;
    public static ExtentTest test;

    public static void setExtent() throws IOException {

        htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"/test-output/ExtentReport/"+"MyReport.html");
        //htmlReporter.loadXMLConfig(System.getProperty("user.dir")+"/extent-config.xml");
        htmlReporter.config().setDocumentTitle("Automation Test Report");
        htmlReporter.config().setReportName("PFL Test Automation Report");
        htmlReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        extent.setSystemInfo("HostName", "MyHost");
        extent.setSystemInfo("ProjectName", "PFLAutomationProject");
        extent.setSystemInfo("Tester", "Hitendra");
        extent.setSystemInfo("OS", "Mac");
        extent.setSystemInfo("Browser", "Chrome");
    }
    public static void endReport() {
        extent.flush();
    }

    }
