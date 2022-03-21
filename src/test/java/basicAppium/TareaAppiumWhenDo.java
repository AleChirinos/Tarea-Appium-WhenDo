package basicAppium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class TareaAppiumWhenDo {
    private AppiumDriver appiumDriver;

    @BeforeEach
    public void openApplication() throws MalformedURLException {
        DesiredCapabilities capabilities= new DesiredCapabilities();
        capabilities.setCapability("deviceName","UPB_QA");
        capabilities.setCapability("platformVersion","9.0");
        capabilities.setCapability("appPackage","com.vrproductiveapps.whendo");
        capabilities.setCapability("appActivity",".ui.HomeActivity");
        capabilities.setCapability("platformName","Android");

        appiumDriver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);

        // implicit
        appiumDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @AfterEach
    public void closeApplication(){
        appiumDriver.quit();
    }

    @Test
    public void verifyTaskCreate() throws InterruptedException {

        String tittle = "Tarea";
        String note = "Esta es la nota";

        //click +
        appiumDriver.findElement(By.id("com.vrproductiveapps.whendo:id/fab")).click();
        // Settear titulo
        appiumDriver.findElement(By.id("com.vrproductiveapps.whendo:id/noteTextTitle")).sendKeys(tittle);
        // Settear Notes
        appiumDriver.findElement(By.id("com.vrproductiveapps.whendo:id/noteTextNotes")).sendKeys(note);
        // Save
        appiumDriver.findElement(By.id("com.vrproductiveapps.whendo:id/saveItem")).click();

        Thread.sleep(2000);

        // Verifico que se haya creado el elemento en mi lista
        String expectedResult=tittle;
        String actualResult= appiumDriver.findElement(By.xpath("//android.widget.TextView[@text='" + tittle + "']")).getText();
        Assertions.assertEquals(expectedResult,actualResult,"ERROR no se creo el elemento de manera correcta");
    }

}
