package com.ajithvgiri.anxial.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ajithvgiri.anxial.data.Result
import com.ajithvgiri.anxial.data.model.BrandResponse
import com.ajithvgiri.anxial.data.repository.HomeRepository
import com.ajithvgiri.anxial.ui.login.LoggedInUserView
import com.ajithvgiri.anxial.ui.login.LoginResult
import javax.inject.Inject

class MainViewModel @Inject constructor(var homeRepository: HomeRepository) : ViewModel() {

    private val _brandResult = MutableLiveData<BrandResult>()
    val brandResult: LiveData<BrandResult> = _brandResult

    private val _productResult = MutableLiveData<ProductResult>()
    val productResult: LiveData<ProductResult> = _productResult

    init {
        brand()
    }

    private fun brand() {
        // can be launched in a separate asynchronous job
        homeRepository.brands { result ->
            if (result is Result.Success) {
                _brandResult.value = BrandResult(success = result.data)
            } else if (result is Result.Error) {
                _brandResult.value = BrandResult(error = result.exception.localizedMessage)
            }
        }
    }

    fun getAllProducts() {
        homeRepository.products { result ->
            if (result is Result.Success) {
                _productResult.value = ProductResult(success = result.data)
            } else if (result is Result.Error) {
                _productResult.value = ProductResult(error = result.exception.localizedMessage)
            }
        }
    }

}