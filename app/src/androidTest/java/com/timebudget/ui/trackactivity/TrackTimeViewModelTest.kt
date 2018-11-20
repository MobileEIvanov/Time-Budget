package com.timebudget.ui.trackactivity


import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import com.timebudget.createTimeEntry
import com.timebudget.data.RepositoryTimeTracker
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class TrackTimeViewModelTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    private val viewStateObserver: Observer<TrackUiModel> = mock()
    private val mockRepository: RepositoryTimeTracker = mock()
    private val viewModel = TrackTimeViewModel(mockRepository)


    @Before
    fun setUp() {
        viewModel.viewState.observeForever(viewStateObserver)
    }


    @Test
    fun test_Repository_insertInvoked() {
        // Arrange
        var timeEntry = createTimeEntry()

        // Act
        viewModel.insert(timeEntry)

        // Assert
        verify(viewModel.repository).insert(timeEntry)
    }
}