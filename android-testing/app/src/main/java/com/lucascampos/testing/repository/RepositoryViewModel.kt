package com.lucascampos.testing.repository

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.lucascampos.testing.data.Api
import com.lucascampos.testing.data.RetrofitConfig.Companion.createApi
import com.lucascampos.testing.data.model.Repository

class RepositoryViewModel(private val api: Api) : ViewModel() {

    val items = MutableLiveData<List<Repository>>()
    val error = MutableLiveData<Throwable>()

    fun fetchRepositories() {
        api.getRepositories()
                .subscribe({
                    items.postValue(it.items)
                }, {
                    error.postValue(it)
                })
    }
}

class RepositoryViewModelFactory(private val baseUrl: String): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel>create(modelClass: Class<T>) = RepositoryViewModel(createApi(baseUrl)) as T
}