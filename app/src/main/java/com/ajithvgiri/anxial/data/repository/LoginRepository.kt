package com.ajithvgiri.anxial.data.repository

import com.ajithvgiri.anxial.data.Result
import com.ajithvgiri.anxial.data.datasource.LoginDataSource
import com.ajithvgiri.anxial.data.model.LoggedInUser
import com.ajithvgiri.anxial.data.model.LoginResponse
import javax.inject.Inject

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class LoginRepository @Inject constructor(val dataSource: LoginDataSource) {

    // in-memory cache of the loggedInUser object
    var user: LoggedInUser?
        get() = dataSource.currentUser

    val isLoggedIn: Boolean
        get() = user != null

    init {
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
        user = dataSource.currentUser
    }

    fun logout() {
        user = null
        dataSource.logout()
    }

    fun login(username: String, password: String, result: (Result<LoginResponse>) -> Unit) =
        dataSource.login(username, password, result)

    private fun setLoggedInUser(loggedInUser: LoggedInUser) {
        this.user = loggedInUser
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }
}