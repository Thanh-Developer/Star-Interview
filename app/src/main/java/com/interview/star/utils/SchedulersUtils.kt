package com.interview.star.utils

import io.reactivex.SingleTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object SchedulersUtils {
    fun <T> applyAsyncSchedulersSingle(): SingleTransformer<T, T> {
        return SingleTransformer { func ->
            func.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
    }
}