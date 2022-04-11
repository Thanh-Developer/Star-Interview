package com.interview.star.ui.base

import androidx.lifecycle.ViewModel

open class BaseTestViewModel<T : ViewModel> : BaseTest() {
    protected lateinit var viewModel: T
}