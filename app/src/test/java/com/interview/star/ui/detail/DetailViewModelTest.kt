package com.interview.star.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.interview.star.ui.base.BaseTestViewModel
import com.interview.star.data.remote.repository.UserRepository
import com.interview.star.utils.FakeUserData.error
import com.interview.star.utils.FakeUserData.userNameTest
import com.interview.star.utils.FakeUserData.userProfileTest
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.spyk
import io.mockk.verify
import io.reactivex.Single
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class DetailViewModelTest : BaseTestViewModel<DetailViewModel>() {
    @Rule
    @JvmField
    val instantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @MockK
    private lateinit var userRepository: UserRepository

    override fun setUp() {
        super.setUp()
        viewModel = spyk(DetailViewModel(userRepository))
    }

    @Test
    fun getUserProfileSuccess() {
        //Given
        every { userRepository.getProfile(userNameTest) } answers {
            Single.just(userProfileTest)
        }

        //When
        viewModel.getProfile(userNameTest)

        //Then
        verify(exactly = 1) {
            viewModel.onLoadSuccess(any())
        }
    }

    @Test
    fun getUserProfileFail() {
        //Given
        every { userRepository.getProfile(userNameTest) } answers {
            Single.error(Throwable(error))
        }

        //When
        viewModel.getProfile(userNameTest)

        //Then
        verify(exactly = 1) {
            viewModel.onLoadFail(any())
        }
    }
}
