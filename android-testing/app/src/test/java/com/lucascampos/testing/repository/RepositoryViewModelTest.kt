package com.lucascampos.testing.repository

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.lucascampos.testing.data.Api
import com.lucascampos.testing.data.model.Owner
import com.lucascampos.testing.data.model.Repository
import com.lucascampos.testing.data.model.RepositoryWrapper
import io.reactivex.Single
import junit.framework.Assert.assertNotNull
import junit.framework.Assert.assertNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

/**
 * @author Lucas Campos
 *
 * Uma abordagem de teste de LiveData/MutableLiveData validando o conteúdo.
 * Neste exemplo estamos apenas verificando a nulidade dos items, mas podemos
 * fazer assertEquals e outras coisas do gênero para validar os dados.
 */
@RunWith(MockitoJUnitRunner::class)
class RepositoryViewModelTest {

    @Mock private lateinit var api: Api

    @get:Rule
    var testRule: TestRule = InstantTaskExecutorRule()

    lateinit var viewModel: RepositoryViewModel

    @Before
    fun setUp() {
        viewModel = RepositoryViewModel(api)
    }

    @Test
    fun shouldFetchRepositoriesWithSuccessful() {
        // given
        `when`(api.getRepositories()).thenReturn(getSuccessResponse())

        // when
        viewModel.fetchRepositories()

        // then
        assertNull(viewModel.error.value)
        assertNotNull(viewModel.items.value)
    }

    @Test
    fun shouldFetchRepositoriesWithError() {
        // given
        `when`(api.getRepositories()).thenReturn(getErrorResponse())

        // when
        viewModel.fetchRepositories()

        // then
        assertNull(viewModel.items.value)
        assertNotNull(viewModel.error.value)
    }

    private fun getSuccessResponse() =
            Single.just(RepositoryWrapper(listOf(Repository("name", Owner("avatar")))))

    private fun getErrorResponse() =
            Single.error<RepositoryWrapper>(Throwable())
}