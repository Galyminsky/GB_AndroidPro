package me.proton.jobforandroid.gbandroidpro.stopwatch.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import me.proton.jobforandroid.gbandroidpro.databinding.ActivityStopwatchBinding
import me.proton.jobforandroid.gbandroidpro.stopwatch.domain.ElapsedTimeCalculator
import me.proton.jobforandroid.gbandroidpro.stopwatch.domain.ITimestampProvider
import me.proton.jobforandroid.gbandroidpro.stopwatch.domain.StopwatchStateCalculator
import me.proton.jobforandroid.gbandroidpro.stopwatch.domain.StopwatchStateHolder
import me.proton.jobforandroid.gbandroidpro.stopwatch.domain.TimestampProviderImpl
import me.proton.jobforandroid.gbandroidpro.stopwatch.domain.model.TimestampMillisecondsFormatter
import me.proton.jobforandroid.gbandroidpro.stopwatch.viewmodel.StopWatchViewModel
import me.proton.jobforandroid.gbandroidpro.stopwatch.viewmodel.StopWatchViewModelFactory

class StopwatchActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStopwatchBinding
    private val timestampProvider: ITimestampProvider = TimestampProviderImpl()
    private val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    private val viewModel: StopWatchViewModel by viewModels {
        StopWatchViewModelFactory(
            StopwatchStateHolder(
                StopwatchStateCalculator(
                    timestampProvider,
                    ElapsedTimeCalculator(timestampProvider),
                ),
                ElapsedTimeCalculator(timestampProvider),
                TimestampMillisecondsFormatter()
            ),
            CoroutineScope(Dispatchers.Main + SupervisorJob())
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStopwatchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()

        scope.launch {
            viewModel.ticker.collect {
                binding.textTimeOne.text = it
            }
        }
    }

    private fun init() {
        binding.buttonStartOne.setOnClickListener {
            viewModel.start()
        }
        binding.buttonPauseOne.setOnClickListener {
            viewModel.pause()
        }
        binding.buttonStopOne.setOnClickListener {
            viewModel.stop()
        }
    }
}