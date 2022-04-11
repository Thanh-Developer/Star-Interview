package com.interview.star.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.interview.star.ui.base.BaseTestViewModel
import com.interview.star.data.remote.repository.UserRepository
import com.interview.star.utils.FakeUserData.error
import com.interview.star.utils.FakeUserData.searchTest
import com.interview.star.utils.FakeUserData.userNameTest
import com.interview.star.utils.FakeUserData.usersTest
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.spyk
import io.mockk.verify
import io.reactivex.Single
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import kotlin.test.assertEquals

class HomeViewModelTest : BaseTestViewModel<HomeViewModel>() {
    @Rule
    @JvmField
    val instantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @MockK
    private lateinit var userRepository: UserRepository

    override fun setUp() {
        super.setUp()
        viewModel = spyk(HomeViewModel(userRepository))
    }

    @Test
    fun getUserSuccess() {
        //Given
        every { userRepository.getAllUsers() } answers {
            Single.just(usersTest)
        }

        //When
        viewModel.fetchData()

        //Then
        verify(exactly = 1) {
            viewModel.onLoadSuccess(any())
        }
    }

    @Test
    fun getUserFail() {
        //Given
        every { userRepository.getAllUsers() } answers {
            Single.error(Throwable(error))
        }

        //When
        viewModel.fetchData()

        //Then
        verify(exactly = 1) {
            viewModel.onLoadFail(any())
        }
    }

    @Test
    fun searchUserSuccess() {
        //Given
        every { userRepository.searchUser(userNameTest) } answers {
            Single.just(searchTest)
        }

        //When
        viewModel.searchUser(userNameTest)

        //Then
        verify(exactly = 1) {
            viewModel.onLoadSuccess(any())
        }
    }

    @Test
    fun searchUserCorrectData() {
        //Given
        every { userRepository.searchUser(userNameTest) } answers {
            Single.just(searchTest)
        }

        //When
        viewModel.searchUser(userNameTest)

        //Then
        verify(exactly = 1) {
            viewModel.onLoadSuccess(any())
        }

        assertEquals(viewModel.users.value?.size, searchTest.items?.size)
    }

    @Test
    fun searchUserFail() {
        //Given
        every { userRepository.searchUser(userNameTest) } answers {
            Single.error(Throwable(error))
        }

        //When
        viewModel.searchUser(userNameTest)

        //Then
        verify(exactly = 1) {
            viewModel.onLoadFail(any())
        }
    }
}
