package com.saucelab.application.tests

import com.saucelab.application.pages.InventoryPage
import com.saucelab.application.pages.LoginPage
import com.saucelab.application.pages.SauceLabBase
import org.testng.Assert
import org.testng.annotations.Test

class InventoryPageTests : SauceLabBase() {

    @Test(enabled = true, priority = 1, description = "Check All Items Available")
    fun inventoryDataCheck() {

        val expectedInventory = arrayOf(
            "Sauce Labs Bike Light", "Sauce Labs Bolt T-Shirt",
            "Sauce Labs Onesie", "Test.allTheThings() T-Shirt (Red)", "Sauce Labs Backpack", "Sauce Labs Fleece Jacket"
        )

        LoginPage.login(driver)
        var actualInventory = InventoryPage.itemOnInventoryPage(driver)
        Assert.assertTrue(actualInventory contentEquals expectedInventory)

    }
}