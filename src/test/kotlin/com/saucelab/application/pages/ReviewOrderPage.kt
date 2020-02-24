package com.saucelab.application.pages

import com.saucelab.application.utils.Control
import com.saucelab.application.utils.Control.Companion.getElementById
import com.saucelab.application.utils.Control.Companion.getElementByXPath
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import org.testng.Assert

class ReviewOrderPage : SauceLabBase() {

    companion object {

        private val url = "https://www.saucedemo.com/checkout-step-two.html"

        private val cartBadgeElement = "//*[@id=\"shopping_cart_container\"]/a/span"
        private val finishButton = "//*[@id=\"checkout_summary_container\"]/div/div[2]/div[8]/a[2]"
        private val totalOrderElement = "//*[@id=\"checkout_summary_container\"]/div/div[2]/div[7]"

        private fun getCartBadgeElement(driver: WebDriver): WebElement {
            return getElementByXPath(driver, cartBadgeElement)
        }

        private fun getTotalOrderElement(driver: WebDriver): WebElement {
            return getElementByXPath(driver, totalOrderElement)
        }

        private fun getFinishButton(driver: WebDriver): WebElement {
            return getElementByXPath(driver, finishButton)
        }

        fun reviewOrder(driver: WebDriver) {

            val wait = WebDriverWait(driver, 10)
            val orderCompletePageUrl = "https://www.saucedemo.com/checkout-complete.html"

            //Assert 6 Items are added
            Assert.assertTrue(getCartBadgeElement(driver).text.equals("6"))

            //Get Order Total
            Assert.assertTrue(getTotalOrderElement(driver).text.equals("Total: \$140.34"))

            //Click Finish
            wait.until(
                ExpectedConditions.elementToBeClickable(getFinishButton(driver))
            ).click()

            //Assert next page is loaded
            Assert.assertTrue(driver.currentUrl.toString().equals(orderCompletePageUrl))

        }


    }
}