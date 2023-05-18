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
import me.proton.jobforandroid.gbandroidpro.presenter.Presenter
import me.proton.jobforandroid.gbandroidpro.presenter.main.MainPresenterImpl
import me.proton.jobforandroid.gbandroidpro.view.BaseActivity
import me.proton.jobforandroid.gbandroidpro.view.View

class MainActivity : BaseActivity<AppState>() {

    private lateinit var binding: ActivityMainBinding

    private val adapter by lazy {
        MainAdapter(onListItemClickListener)
    }

    private val onListItemClickListener: MainAdapter.OnListItemClickListener =
        object : MainAdapter.OnListItemClickListener {
            override fun onItemClick(data: DataModel) {
                Toast.makeText(this@MainActivity, data.text, Toast.LENGTH_SHORT).show()
            }
        }

    override fun createPresenter(): Presenter<AppState, View> {
        return MainPresenterImpl()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            inputLayout.setEndIconOnClickListener {
                presenter.getData(inputEditText.text.toString(), true)
            }
            inputEditText.setOnEditorActionListener { _, actionId, _ ->
                println(actionId)
                if (actionId == EditorInfo.IME_ACTION_UNSPECIFIED || actionId == EditorInfo.IME_ACTION_SEARCH) {
                    presenter.getData(inputEditText.text.toString(), true)
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
                    if (dataModel == null || dataModel.isEmpty()) {
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
                presenter.getData("hi", true)
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
