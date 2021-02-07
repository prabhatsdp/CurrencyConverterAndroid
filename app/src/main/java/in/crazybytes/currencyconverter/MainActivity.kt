package `in`.crazybytes.currencyconverter

import `in`.crazybytes.currencyconverter.databinding.ActivityMainBinding
import `in`.crazybytes.currencyconverter.main.MainViewModel
import android.graphics.Color
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//
//        binding.btnConvert.setOnClickListener {
//            viewModel.convert(
//                binding.etFrom.text.toString(),
//                binding.spFromCurrency.selectedItem.toString(),
//                binding.spToCurrency.selectedItem.toString(),
//            )
//        }
//
//        lifecycleScope.launchWhenStarted {
//
//            viewModel.conversion.collect { event ->
//                when (event) {
//                    is MainViewModel.CurrencyRateEvent.Success -> {
//                        binding.progressBar.isVisible = false
//                        binding.tvResult.setTextColor(Color.BLACK)
//                        binding.tvResult.text = event.resultText
//                    }
//                    is MainViewModel.CurrencyRateEvent.Failure -> {
//                        binding.progressBar.isVisible = false
//                        binding.tvResult.setTextColor(Color.RED)
//                        binding.tvResult.text = event.errorText
//                    }
//                    is MainViewModel.CurrencyRateEvent.Loading -> {
//                        binding.progressBar.isVisible = true
//                    }
//                    else -> Unit
//                }
//            }
//        }

    }

}
