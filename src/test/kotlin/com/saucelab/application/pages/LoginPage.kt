package com.saucelab.application.pages

import com.saucelab.application.utils.Control.Companion.getElementByCSS
import com.saucelab.application.utils.Control.Companion.getElementById
import com.saucelab.application.utils.Control.Companion.getElementByXPath
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import org.testng.Assert

class LoginPage : SauceLabBase() {

    companion object {

        val url = "https://www.saucedemo.com"

        private val standardUser = "standard_user"
        private val validPassword = "secret_sauce"
        private val userNameElement = "user-name"
        private val passwordElement = "password"
        private val loginButton = "//*[@id=\"login_button_container\"]/div/form/input[3]"
        private val errorCSS = "#login_button_container > div > form > h3 > button > svg"
        private val topDropDownXPath = "//*[@id=\"menu_button_container\"]/div/div[3]/div/button"
        private val logOutElement = "logout_sidebar_link"

         fun getUserNameBox(driver: WebDriver): WebElement {
            return getElementById(driver, userNameElement)
        }

         fun getPasswordBox(driver: WebDriver): WebElement {
            return getElementById(driver, passwordElement)
        }

         fun getLoginButton(driver: WebDriver): WebElement {
            return getElementByXPath(driver, loginButton)
        }

         fun getTopDropDown(driver: WebDriver): WebElement {
            return getElementByXPath(driver, topDropDownXPath)
        }

         fun getLogOutElement(driver: WebDriver): WebElement {
            return getElementById(driver, logOutElement)
        }

        fun getError(driver: WebDriver): WebElement {
            return getElementByCSS(driver, errorCSS)
        }

        fun verifyPageElementsDisplayed(driver: WebDriver) {

            Assert.assertTrue(getUserNameBox(driver).isDisplayed)
            Assert.assertTrue(getPasswordBox(driver).isDisplayed)
            Assert.assertTrue(getLoginButton(driver).isDisplayed)

        }

        fun clearLogin (driver: WebDriver) {
            //Clear fields
            LoginPage.getUserNameBox(driver).clear()
            LoginPage.getPasswordBox(driver).clear()
        }

        fun logOut (driver: WebDriver) {
            LoginPage.getTopDropDown(driver).click()
            LoginPage.getLogOutElement(driver).click()
        }

        fun login(driver: WebDriver) {
            val wait = WebDriverWait(driver, 15)
            val successfulSignInUrl = "https://www.saucedemo.com/inventory.html"

            //Input User name
            getUserNameBox(driver).sendKeys(standardUser)
            //Input Password
            getPasswordBox(driver).sendKeys(validPassword)
            //Click Login
            wait.until(ExpectedConditions.elementToBeClickable(getLoginButton(driver))).click()
            //On Successful log in assert on inventory page
            Assert.assertTrue(driver.currentUrl.toString().equals(successfulSignInUrl))
        }
    }
}