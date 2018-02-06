package com.lucascampos.testing.repository

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.lucascampos.testing.App
import com.lucascampos.testing.R
import kotlinx.android.synthetic.main.activity_repository.*

class RepositoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repository)

        setupViewModel()
    }

    private fun setupViewModel() {
        val viewModel = ViewModelProviders.of(this, RepositoryViewModelFactory(getBaseUrl())).get(RepositoryViewModel::class.java)

        viewModel.items.observe(this, Observer {
            recycler_view.layoutManager = LinearLayoutManager(this)

            if (it?.isEmpty() == false) {
                recycler_view.adapter = RepositoryAdapter(it)
            } else {
                view_flipper.displayedChild = 1
            }
        })

        viewModel.error.observe(this, Observer {
            Toast.makeText(this, "#erro", Toast.LENGTH_SHORT).show()
        })

        viewModel.fetchRepositories()
    }


    private fun getBaseUrl() = (application as App).getUrl()

}