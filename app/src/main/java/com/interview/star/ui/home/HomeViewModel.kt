package com.interview.star.ui.home

import androidx.lifecycle.MutableLiveData
import com.interview.star.data.remote.repository.UserRepository
import com.interview.star.data.remote.model.User
import com.interview.star.ui.base.BaseViewModel
import com.interview.star.utils.SchedulersUtils.applyAsyncSchedulersSingle
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomeViewModel(private val userRepository: UserRepository) : BaseViewModel() {
    val users = MutableLiveData<List<User>>().apply {
        value = mutableListOf()
    }

    fun fetchData() {
        addDisposable(userRepository.getAllUsers().observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .compose(applyAsyncSchedulersSingle())
            .doOnSubscribe { showLoading() }
            .doFinally { hideLoading() }
            .subscribe(
                {
                    onLoadSuccess(it)
                }, {
                    onLoadFail(it)
                }
            ))
    }

    fun searchUser(key: String) {
        addDisposable(userRepository.searchUser(key).observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .compose(applyAsyncSchedulersSingle())
            .doOnSubscribe { showLoading() }
            .doFinally { hideLoading() }
            .subscribe(
                {
                    it.items?.let { items -> onLoadSuccess(items) }
                }, {
                    onLoadFail(it)
                }
            ))
    }

    fun onLoadSuccess(data: List<User>) {
        users.value = data
    }

}