package com.saucelab.application.pages

import com.saucelab.application.utils.UtilResources
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.testng.annotations.AfterClass
import org.testng.annotations.AfterTest
import org.testng.annotations.BeforeTest
import java.net.URI
import java.util.concurrent.TimeUnit
import java.util.logging.Logger

abstract class SauceLabBase {

    lateinit var driver: WebDriver
    val log = Logger.getLogger(SauceLabBase::class.java.name)

    @BeforeTest
    fun setup() {

        log.info("Set Up Driver \n")

        System.setProperty(UtilResources.getProperties("nameDriver"), UtilResources.getProperties("pathDriver")
                + UtilResources.getProperties("exeDriver"))
        driver = ChromeDriver()
        driver.manage()?.timeouts()?.implicitlyWait(5, TimeUnit.SECONDS)
        driver.get(URI(UtilResources.getProperties("pageURL")).toString())

        log.info("Page URL: ${driver.currentUrl} \n")

    }

    @AfterTest
    fun tearDown() {

        driver.close()

        log.info("Driver Closed \n")
    }
}