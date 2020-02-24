package com.saucelab.application.tests

import com.saucelab.application.dataProviders.LoginDataProvider
import com.saucelab.application.pages.LoginPage
import com.saucelab.application.pages.SauceLabBase
import com.saucelab.application.models.Result

import org.openqa.selenium.support.ui.ExpectedConditions
import org.testng.Assert
import org.testng.annotations.Test
import org.openqa.selenium.support.ui.WebDriverWait


class LoginTests : SauceLabBase() {

    @Test(enabled = true, priority = 1, description = "Verify Elements on Login Page")
    fun loginPageDisplayTests() {

        LoginPage.verifyPageElementsDisplayed(driver)

    }

    @Test(enabled = true, priority = 1, description = "Login Using different passwords", dataProvider = "Login Credentials",
        dataProviderClass = LoginDataProvider::class)
    fun loginTests(userName: String, password: String, expectedResult: Result) {

        val wait = WebDriverWait(driver, 15)
        val successfulSignInUrl = "https://www.saucedemo.com/inventory.html"
        val loginUrl = "https://www.saucedemo.com/index.html"

        //Clear Fields
        LoginPage.clearLogin(driver)
        //Input User name
        LoginPage.getUserNameBox(driver).sendKeys(userName)
        //Input Password
        LoginPage.getPasswordBox(driver).sendKeys(password)
        //Click Login
        wait.until(ExpectedConditions.elementToBeClickable(LoginPage.getLoginButton(driver))).click()

        if (expectedResult.equals(Result.SUCCESS)) {
            //On Successful log in assert on inventory page
            Assert.assertTrue(driver.currentUrl.toString().equals(successfulSignInUrl))
            LoginPage.logOut(driver)

        }
        else {
            //On Error assert on error button
            wait.until(ExpectedConditions.elementToBeClickable(LoginPage.getError(driver)))
            Assert.assertTrue(LoginPage.getError(driver).isDisplayed)
            Assert.assertTrue(driver.currentUrl.toString().equals(loginUrl))
            LoginPage.getError(driver).click()
        }

    }

}