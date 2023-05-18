package me.proton.jobforandroid.gbandroidpro.view.main

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import me.proton.jobforandroid.gbandroidpro.R
import me.proton.jobforandroid.gbandroidpro.databinding.ActivityMainBinding
import me.proton.jobforandroid.gbandroidpro.model.AppState
import me.proton.jobforandroid.gbandroidpro.model.repository.entity.DataModel
import me.proton.jobforandroid.gbandroidpro.view.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<AppState, MainInteractor>() {

    private lateinit var binding: ActivityMainBinding

    override val model: MainViewModel by viewModel()

    private val adapter by lazy {
        MainAdapter(onListItemClickListener)
    }

    private val onListItemClickListener: MainAdapter.OnListItemClickListener =
        object : MainAdapter.OnListItemClickListener {
            override fun onItemClick(data: DataModel) {
                Toast.makeText(this@MainActivity, data.text, Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        model.subscribe().observe(this@MainActivity) { renderData(it) }

        binding.apply {

            inputLayout.setEndIconOnClickListener {
                model.getData(inputEditText.text.toString(), isNetworkAvailable)
            }

            inputEditText.setOnEditorActionListener { _, actionId, _ ->
                println(actionId)
                if (actionId == EditorInfo.IME_ACTION_UNSPECIFIED || actionId == EditorInfo.IME_ACTION_SEARCH) {
                    model.getData(inputEditText.text.toString(), isNetworkAvailable)
                    return@setOnEditorActionListener true
                }
                return@setOnEditorActionListener false
            }

            mainActivityRecyclerview.layoutManager = LinearLayoutManager(applicationContext)
            mainActivityRecyclerview.adapter = adapter
        }
    }

    override fun renderData(appState: AppState) {
        binding.apply {
            when (appState) {
                is AppState.Success -> {
                    val dataModel = appState.data
                    if (dataModel.isNullOrEmpty()) {
                        showErrorScreen(getString(R.string.empty_server_response_on_success))
                    } else {
                        showViewSuccess()
                        adapter.setData(dataModel)
                    }
                }

                is AppState.Loading -> {
                    showViewLoading()
                    if (appState.progress != null) {
                        progressBarHorizontal.visibility = VISIBLE
                        progressBarRound.visibility = GONE
                        progressBarHorizontal.progress = appState.progress
                    } else {
                        progressBarHorizontal.visibility = GONE
                        progressBarRound.visibility = VISIBLE
                    }
                }

                is AppState.Error -> {
                    showErrorScreen(appState.error.message)
                }
            }
        }
    }

    private fun showErrorScreen(error: String?) {
        showViewError()
        binding.apply {
            errorTextview.text = error ?: getString(R.string.undefined_error)
            reloadButton.setOnClickListener {
                model.getData("hi", isNetworkAvailable)
            }
        }
    }

    private fun showViewSuccess() {
        binding.apply {
            loadingFrameLayout.visibility = GONE
            errorLinearLayout.visibility = GONE
        }
    }

    private fun showViewLoading() {
        binding.apply {
            loadingFrameLayout.visibility = VISIBLE
            errorLinearLayout.visibility = GONE
        }
    }

    private fun showViewError() {
        binding.apply {
            loadingFrameLayout.visibility = GONE
            errorLinearLayout.visibility = VISIBLE
        }
    }
}