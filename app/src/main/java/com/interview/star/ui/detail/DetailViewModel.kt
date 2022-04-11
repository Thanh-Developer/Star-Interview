package com.interview.star.ui.detail

import androidx.lifecycle.MutableLiveData
import com.interview.star.data.remote.repository.UserRepository
import com.interview.star.data.remote.model.Profile
import com.interview.star.data.remote.model.User
import com.interview.star.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DetailViewModel(private val userRepository: UserRepository) : BaseViewModel() {
    val user = MutableLiveData<User>()
    val profile = MutableLiveData<Profile>()
    val isBackPress = MutableLiveData(false)

    fun getProfile(userName: String) {
        addDisposable(userRepository.getProfile(userName).observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
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

    fun onBackPressClick(){
        isBackPress.value = true
    }

    fun onLoadSuccess(data: Profile) {
        profile.value = data
    }
}