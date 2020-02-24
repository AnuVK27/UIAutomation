package com.saucelab.application.dataProviders

import com.saucelab.application.models.Result
import org.testng.annotations.DataProvider

object LoginDataProvider {

    @DataProvider(name = "Login Credentials")
    @JvmStatic
    fun generateUserCredentials(): Array<Array<Any>> {

        val standardUser = "standard_user"
        val lockedUser = "locked_out_user"
        val performanceGlitchUser = "problem_user"
        val problemUser = "performance_glitch_user"
        val validPassword = "secret_sauce"
        val invalidPassword = "invalidPassword"

        return arrayOf(
            //Valid Login
            arrayOf(standardUser, validPassword, Result.SUCCESS),
            arrayOf(performanceGlitchUser, validPassword, Result.SUCCESS),
            arrayOf(problemUser, validPassword, Result.SUCCESS),
            //Invalid Login
            arrayOf(standardUser, invalidPassword, Result.FAILURE),
            arrayOf(lockedUser, validPassword, Result.FAILURE)
            )

    }
}