package me.proton.jobforandroid.gbandroidpro.view.history

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import me.proton.jobforandroid.gbandroidpro.databinding.ActivityHistoryBinding
import me.proton.jobforandroid.gbandroidpro.model.AppState
import me.proton.jobforandroid.gbandroidpro.model.repository.entity.DataModel
import me.proton.jobforandroid.gbandroidpro.view.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class HistoryActivity : BaseActivity<AppState, HistoryInteractor>() {

    private lateinit var binding: ActivityHistoryBinding

    override val model: HistoryViewModel by viewModel()

    private val adapter: HistoryAdapter by lazy { HistoryAdapter(onListItemClickListener) }

    private val onListItemClickListener: HistoryAdapter.OnListItemClickListener =
        object : HistoryAdapter.OnListItemClickListener {
            override fun onItemClick(data: DataModel) {
                Toast.makeText(this@HistoryActivity, "on click: ${data.text}", Toast.LENGTH_SHORT)
                    .show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setActionbarHomeButtonAsUp()
        iniViewModel()
        initViews()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onResume() {
        super.onResume()
        model.getData("", false)
    }

    private fun iniViewModel() {
        binding.apply {
            if (historyActivityRecyclerview.adapter != null) {
                throw IllegalStateException("The ViewModel should be initialised first")
            }
            model.subscribe().observe(this@HistoryActivity) { renderData(it) }
        }
    }

    private fun initViews() {
        binding.apply {
            historyActivityRecyclerview.adapter = adapter
        }
    }

    override fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                val data = appState.data
                if (!data.isNullOrEmpty()) {
                    adapter.setData(data)
                }
            }

            else -> {

            }
        }
    }

    private fun setActionbarHomeButtonAsUp() {
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}