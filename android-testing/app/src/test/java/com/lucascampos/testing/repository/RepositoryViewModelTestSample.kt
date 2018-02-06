package com.lucascampos.testing.repository

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import com.lucascampos.testing.data.Api
import com.lucascampos.testing.data.model.Owner
import com.lucascampos.testing.data.model.Repository
import com.lucascampos.testing.data.model.RepositoryWrapper
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

/**
 * @author Lucas Campos
 */
@RunWith(MockitoJUnitRunner::class)
class RepositoryViewModelTestSample {

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @Mock private lateinit var api: Api
    @Mock private lateinit var itemsObserver: Observer<List<Repository>>
    @Mock private lateinit var errorObserver: Observer<Throwable>

    lateinit var viewModel: RepositoryViewModel

    @Before
    fun setUp() {
        viewModel = RepositoryViewModel(api)

        viewModel.items.observeForever(itemsObserver)
        viewModel.error.observeForever(errorObserver)
    }

    @Test
    fun shouldFetchRepositoriesWithSuccessful() {
        // given
        `when`(api.getRepositories()).thenReturn(Single.just(getRepositories()))

        // when
        viewModel.fetchRepositories()

        // then
        verify(itemsObserver).onChanged(viewModel.items.value)
    }

    @Test
    fun shouldFetchRepositoriesWithError() {
        // given
        `when`(api.getRepositories()).thenReturn(Single.error(Throwable()))

        // when
        viewModel.fetchRepositories()

        // then
        verify(errorObserver).onChanged(viewModel.error.value)
    }

    private fun getRepositories() =
            RepositoryWrapper(listOf(Repository("Kotlin", Owner("avatar"))))
}