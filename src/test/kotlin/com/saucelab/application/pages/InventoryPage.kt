package com.saucelab.application.pages

import com.saucelab.application.utils.Control.Companion.getElementByCSS
import com.saucelab.application.utils.Control.Companion.getElementByXPath
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import org.testng.Assert

class InventoryPage : SauceLabBase() {

    companion object {

        private val url = "https://www.saucedemo.com/inventory.html"
        private val fragment_1_of_item_element = "//*[@id=\"item_"
        private val fragment_2_of_item_element = "_title_link\"]/div"
        private val fragment_1_of_add_to_cart_element = "//*[@id=\"inventory_container\"]/div/div["
        private val fragment_2_of_add_to_cart_element = "]/div[3]/button"
        private val cartCount = "//*[@id=\"shopping_cart_container\"]/a/span"
        private val cartElement = "#shopping_cart_container > a > svg > path"
        private val checkOurButtonElement = "#cart_contents_container > div > div.cart_footer > a.btn_action.checkout_button"

        //Add all Items to Cart
        fun addToCart(driver: WebDriver) {

            val wait = WebDriverWait(driver, 10)
            val checkoutStepOnePageUrl = "https://www.saucedemo.com/checkout-step-one.html"

            for (x in 1..6) {
                wait.until(
                    ExpectedConditions.elementToBeClickable(
                        By.ByXPath(
                            fragment_1_of_add_to_cart_element + x + fragment_2_of_add_to_cart_element
                        )
                    )
                ).click()
            }

            //Assert 6 Items were added to cart
            Assert.assertTrue(getElementByXPath(driver, cartCount).text.equals("6"))

            //Click on Cart
            wait.until(
                ExpectedConditions.elementToBeClickable(getElementByCSS(driver,cartElement))).click()

            //Checkout
            wait.until(
                ExpectedConditions.elementToBeClickable(getElementByCSS(driver,checkOurButtonElement))).click()

            //Assert next page is loaded
            Assert.assertTrue(driver.currentUrl.toString().equals(checkoutStepOnePageUrl))
        }

        //Return Name of Items on that Page
        fun itemOnInventoryPage(driver: WebDriver): Array<String> {

            val wait = WebDriverWait(driver, 15)

            var actualInventory = arrayOf(
                getElementByXPath(
                    driver,
                    fragment_1_of_item_element + "0" + fragment_2_of_item_element
                ).text
            )

            for (x in 1..5) {

                wait.until(
                    ExpectedConditions.presenceOfAllElementsLocatedBy(
                        By.ByXPath(
                            fragment_1_of_item_element + x + fragment_2_of_item_element
                        )
                    )
                )

                actualInventory += getElementByXPath(
                    driver,
                    fragment_1_of_item_element + x + fragment_2_of_item_element
                ).text
            }
            return actualInventory

        }

    }
}