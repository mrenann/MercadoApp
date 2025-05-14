package com.mrenann.mercadolivre.homeScreen.presentation.screenModel

import io.mockk.clearAllMocks
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeScreenModelTest {
    private val dispatcher = StandardTestDispatcher()
    private lateinit var screenModel: HomeScreenModel

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        clearAllMocks()
        screenModel = HomeScreenModel()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `initial state should be Loading`() = runTest {
        assertTrue(screenModel.state.value is HomeScreenModel.State.Loading)
    }

    @Test
    fun `after delay state should be Result`() = runTest {
        advanceTimeBy(2000L)
        runCurrent()
        assertTrue(screenModel.state.value is HomeScreenModel.State.Result)
    }
}