package com.ensono.app.utils;

import java.awt.Toolkit;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.openqa.selenium.safari.SafariDriver;

import com.ensono.app.constants.Browsers;

import org.openqa.selenium.ie.InternetExplorerDriver;


public class DriverManager {
	private static final Logger LOGGER = Logger.getLogger(DriverManager.class.getName());

	private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

	public synchronized static WebDriver getDriver(final String browserName) {
		 if (driver.get() == null) {
			 setWebDriver(createBrowserInstance(browserName));
		}        
		 return driver.get();
   }
	
	public synchronized static WebDriver getDriver() {
		 if (driver.get() == null) {
			 setWebDriver(createBrowserInstance(null));
		}        
		 return driver.get();
    }

    public static void setWebDriver(WebDriver driver) {       
        DriverManager.driver.set(driver);
    }
    
//	private static class BrowserCleanup implements Runnable {
//        public void run() {
//            close();
//        }
//    }

	/**
	 * @return WebDriver
	 */
	private static WebDriver createBrowserInstance(final String browserName) {
		Browsers browser;
		WebDriver driver;
			    
		LOGGER.info("BROWSER_NAME:" + browserName);
		if(browserName==null){
			browser = Browsers.FIREFOX;
		}else{
			browser = Browsers.browserForName(browserName);
		}
		
		switch(browser){
			case CHROME:
				System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
				driver = new ChromeDriver();			
				break;
			case IE:
				System.setProperty("webdriver.ie.driver", "src/main/resources/IEDriverServer.exe");
				driver = new InternetExplorerDriver();
				break;
			case FIREFOX:
			default:
				driver = new FirefoxDriver(getFirefoxProfile());
				break;
		}
		addAllBrowserSetup(driver);
		return driver;
	}


	private static FirefoxProfile getFirefoxProfile() {
        FirefoxProfile firefoxProfile = new FirefoxProfile();
     //   try {
	//		firefoxProfile.addExtension(FileUtils.getFile("firebug/firebug-1.9.2.xpi"));
    //    } catch (IOException e) {
    //        e.printStackTrace();
    //    } catch (URISyntaxException e){
    //    	e.printStackTrace();
    //    }

        //See http://getfirebug.com/wiki/index.php/Firebug_Preferences
     //   firefoxProfile.setPreference("extensions.firebug.currentVersion", "1.9.2");  // Avoid startup screen
     //   firefoxProfile.setPreference("extensions.firebug.script.enableSites", true);
     //   firefoxProfile.setPreference("extensions.firebug.console.enableSites", true);
     //   firefoxProfile.setPreference("extensions.firebug.allPagesActivation", true);
     //   firefoxProfile.setPreference("extensions.firebug.delayLoad", false);
        return firefoxProfile;
    }
	
	private static void addAllBrowserSetup(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().setPosition(new Point(0, 0));
        java.awt.Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension dim = new Dimension((int) screenSize.getWidth(), (int) screenSize.getHeight());
        driver.manage().window().setSize(dim);        
	}

	
    /**
     * Returns a string containing current browser name, its version and OS name.     
     * */
    public static String getBrowserInfo(){
    	LOGGER.info("Getting browser info");        
        // we have to cast WebDriver object to RemoteWebDriver here, because the first one does not have a method
        // that would tell you which browser it is driving. (sick!)
        Capabilities cap = ((RemoteWebDriver) DriverManager.getDriver()).getCapabilities();
        String b = cap.getBrowserName();
        String os = cap.getPlatform().toString();
        String v = cap.getVersion();
        return String.format("%s v:%s %s", b, v, os);
    }

	public static void close() {
        try {
            getDriver().quit();  
            if (driver.get() != null) {
            driver.set(null);            	
            }
            LOGGER.info("closing the browser");
        } catch (UnreachableBrowserException e) {
            LOGGER.info("cannot close browser: unreachable browser");
        }
    }
    
}

