package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;

import com.qa.opencart.errors.AppError;
import com.qa.opencart.exceptions.BowserExceptions;
import com.qa.opencart.exceptions.FrameworkException;
import com.qa.opencart.logger.Log;

public class DriverFactory {
	WebDriver driver=null;
	Properties prop;
	OptionsManager optionsManager;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	public static String highlight;
	public WebDriver initDiver(Properties prop) {
		String BrowserName=prop.getProperty("browser");
		Log.info("Browser name is: "+BrowserName);
		
		highlight = prop.getProperty("highlight");

		optionsManager = new OptionsManager(prop);
		switch (BrowserName.toLowerCase().trim()) {
		case "chrome":
			//driver=new ChromeDriver();
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			break;
			
		case "firefox":
			//driver=new FirefoxDriver();
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			break;
		case "edge":
			//driver=new EdgeDriver();
			tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
			break;

		default:
		//	System.out.println("Please pass correct browser"+BrowserName);
			Log.error("Please pass correct browser"+BrowserName);
			throw new BowserExceptions("Incorrect BrowserName"+BrowserName);
			//break;
		}
		getDriver().manage().deleteAllCookies();
		
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
	return getDriver();
	}
	
	public static WebDriver getDriver() {
		return tlDriver.get();
	}
	
	public Properties initprop() {
//		prop=new Properties();
//		try {
//			FileInputStream fi=new FileInputStream("./src/test/resources/config/config.properties");
//			prop.load(fi);
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch(IOException e) {
//			e.printStackTrace();
//		}
//		return prop;
//	}
		
		FileInputStream ip = null;
		prop = new Properties();

		String envName = System.getProperty("env");
		System.out.println("Running tests on Env: " + envName);

		try {
			if (envName == null) {
				System.out.println("No env is given...hence running it on QA env...");
				ip = new FileInputStream("./src/test/resources/config/config.qa.properties");
			} else {

				switch (envName.toLowerCase().trim()) {
				case "qa":
					ip = new FileInputStream("./src/test/resources/config/config.qa.properties");
					break;
				case "dev":
					ip = new FileInputStream("./src/test/resources/config/config.dev.properties");
					break;
				case "stage":
					ip = new FileInputStream("./src/test/resources/config/config.stage.properties");
					break;
				case "uat":
					ip = new FileInputStream("./src/test/resources/config/config.uat.properties");
					break;
				case "prod":
					ip = new FileInputStream("./src/test/resources/config/config.properties");
					break;

				default:
					System.out.println("plz pass the right env name... " + envName);
					throw new FrameworkException(AppError.ENV_NAME_NOT_FOUND + " : " + envName);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return prop;
	}

	//TakeScreenshot
	public static String getScreenshot(String methodName) {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);//temp directory
		String path = System.getProperty("user.dir") + "/screenshot/" + methodName + "_" + System.currentTimeMillis()
				+ ".png";

		File destination = new File(path);

		try {
			FileHandler.copy(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return path;
	}
}
