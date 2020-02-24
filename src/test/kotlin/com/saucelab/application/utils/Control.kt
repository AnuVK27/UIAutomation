package com.saucelab.application.utils

import org.openqa.selenium.By
import org.openqa.selenium.Dimension
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

open class Control {

    companion object {

        fun getElementById(driver: WebDriver, id: String) : WebElement {
            return driver.findElement(By.ById(id))
        }

        fun getElementByXPath(driver: WebDriver, xpath: String) : WebElement {
            return driver.findElement(By.ByXPath(xpath))
        }

        fun getElementByCSS(driver: WebDriver, selector: String) : WebElement {
            return driver.findElement(By.ByCssSelector(selector))
        }

        fun getSize(el : WebElement) : ArrayList<Int>{
            return arrayListOf(el.size.getHeight().toInt(), el.size.getWidth().toInt())
        }

    }
}