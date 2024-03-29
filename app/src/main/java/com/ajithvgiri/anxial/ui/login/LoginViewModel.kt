package com.ajithvgiri.anxial.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Patterns
import com.ajithvgiri.anxial.data.repository.LoginRepository
import com.ajithvgiri.anxial.data.Result

import com.ajithvgiri.anxial.R
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val loginRepository: LoginRepository) :
    ViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    fun checkUserLoggedIn() {
        if (loginRepository.isLoggedIn){
            _loginResult.value =
                LoginResult(success = LoggedInUserView(token = ""))
        }
    }

    fun login(username: String, password: String) {
        // can be launched in a separate asynchronous job
        loginRepository.login(username, password) { result ->
        if (result is Result.Success) {
            _loginResult.value =
                LoginResult(success = LoggedInUserView(token = result.data.data?.access_token!!))
        } else if(result is Result.Error){
            _loginResult.value = LoginResult(error = result.exception.localizedMessage)
        }
    }
}

fun loginDataChanged(username: String, password: String) {
    if (!isUserNameValid(username)) {
        _loginForm.value = LoginFormState(usernameError = R.string.invalid_email)
    } else if (!isPasswordValid(password)) {
        _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
    } else {
        _loginForm.value = LoginFormState(isDataValid = true)
    }
}

// A placeholder username validation check
private fun isUserNameValid(username: String): Boolean {
    return if (username.contains('@')) {
        Patterns.EMAIL_ADDRESS.matcher(username).matches()
    } else {
        username.isNotBlank()
    }
}

// A placeholder password validation check
private fun isPasswordValid(password: String): Boolean {
    return password.length > 5
}
}