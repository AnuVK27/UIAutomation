package com.saucelab.application.tests

import com.saucelab.application.pages.*
import org.testng.annotations.Test

class CheckoutTests : SauceLabBase() {

    @Test(enabled = true, priority = 1, description = "Add all Items and checkout tests")
    fun checkOutTests() {

        LoginPage.login(driver)
        InventoryPage.addToCart(driver)
        InputInformationPage.checkOutStepOne(driver)
        ReviewOrderPage.reviewOrder(driver)
        OrderCompletePage.checkOrderComplete(driver)

    }
}