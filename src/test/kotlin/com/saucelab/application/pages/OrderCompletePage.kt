package com.saucelab.application.pages

import com.saucelab.application.utils.Control.Companion.getElementByXPath
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.testng.Assert

class OrderCompletePage : SauceLabBase() {

    companion object {

        private val url = "https://www.saucedemo.com/checkout-complete.html"

        private val successTextElement = "//*[@id=\"checkout_complete_container\"]/h2"

        private val message = "THANK YOU FOR YOUR ORDER"

        private fun getSuccessMessage(driver: WebDriver): WebElement {

            return getElementByXPath(driver, successTextElement)
        }

        fun checkOrderComplete(driver: WebDriver) {

            Assert.assertTrue(getSuccessMessage(driver).text.equals("THANK YOU FOR YOUR ORDER"))

        }
    }
}