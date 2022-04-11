package com.interview.star.ui.base

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel : LifecycleObserver, ViewModel() {

    val isLoading = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()

    private val compositeDisposable = CompositeDisposable()

    protected fun showLoading() {
        isLoading.postValue(true)
    }

    protected fun hideLoading() {
        isLoading.postValue(false)
    }

    protected fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        compositeDisposable.clear()
    }

    @VisibleForTesting
    open fun onLoadFail(throwable: Throwable) {
        throwable.message?.let {
            errorMessage.value = it
        }
    }

    open fun onLoadSuccess() {}

}