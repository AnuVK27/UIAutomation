package com.saucelab.application.pages

import com.saucelab.application.utils.Control.Companion.getElementById
import com.saucelab.application.utils.Control.Companion.getElementByXPath
import net.bytebuddy.utility.RandomString
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import org.testng.Assert


class InputInformationPage : SauceLabBase() {

    companion object {

        private val url = "https://www.saucedemo.com/checkout-step-one.html"

        private val firstNameElement = "first-name"
        private val lastNameElement = "last-name"
        private val zipCodeElement = "postal-code"
        private val continueButtonElement = "//*[@id=\"checkout_info_container\"]/div/form/div[2]/input"

        fun getFirstNameElement(driver: WebDriver): WebElement {
            return getElementById(driver, firstNameElement)
        }

        fun getLastNameElement(driver: WebDriver): WebElement {
            return getElementById(driver, lastNameElement)
        }

        fun getZipCodeElement(driver: WebDriver): WebElement {
            return getElementById(driver, zipCodeElement)
        }

        fun getContinueButton(driver: WebDriver): WebElement {
            return getElementByXPath(driver, continueButtonElement)
        }

        fun checkOutStepOne(driver: WebDriver) {

            val wait = WebDriverWait(driver, 10)
            val checkoutStepTwoPageUrl = "https://www.saucedemo.com/checkout-step-two.html"

            getFirstNameElement(driver).sendKeys(RandomString.make(8))
            getLastNameElement(driver).sendKeys(RandomString.make(8))
            getZipCodeElement(driver).sendKeys(RandomString.make(5))
            wait.until(ExpectedConditions.elementToBeClickable(getContinueButton(driver))).click()

            //Assert next page is loaded
            Assert.assertTrue(driver.currentUrl.toString().equals(checkoutStepTwoPageUrl))

        }

    }
}