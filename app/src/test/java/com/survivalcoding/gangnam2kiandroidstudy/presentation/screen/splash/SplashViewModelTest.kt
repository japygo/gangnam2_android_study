@file:OptIn(ExperimentalCoroutinesApi::class)

package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.splash

import com.survivalcoding.gangnam2kiandroidstudy.core.util.NetworkMonitor
import com.survivalcoding.gangnam2kiandroidstudy.test.MainDispatcherRule
import io.mockk.MockKAnnotations
import io.mockk.bdd.given
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SplashViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @MockK
    private lateinit var networkMonitor: NetworkMonitor

    private lateinit var viewModel: SplashViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun testIsOnline() = runTest {
        given { networkMonitor.networkState } returns flowOf(true)

        viewModel = SplashViewModel(networkMonitor)

        advanceUntilIdle()

        assertTrue(viewModel.uiState.value.isOnline)
    }

    @Test
    fun testIsOffline() = runTest {
        given { networkMonitor.networkState } returns flowOf(false)

        viewModel = SplashViewModel(networkMonitor)

        advanceUntilIdle()

        assertFalse(viewModel.uiState.value.isOnline)
    }

    @Test
    fun changeNetworkState() = runTest {
        val networkFlow = MutableSharedFlow<Boolean>()
        given { networkMonitor.networkState } returns networkFlow

        viewModel = SplashViewModel(networkMonitor)

        networkFlow.emit(true)
        advanceUntilIdle()

        assertTrue(viewModel.uiState.value.isOnline)

        networkFlow.emit(false)
        advanceUntilIdle()

        assertFalse(viewModel.uiState.value.isOnline)
    }
}