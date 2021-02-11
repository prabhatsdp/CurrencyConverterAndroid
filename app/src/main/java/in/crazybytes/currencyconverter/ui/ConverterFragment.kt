package `in`.crazybytes.currencyconverter.ui

import `in`.crazybytes.currencyconverter.R
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import `in`.crazybytes.currencyconverter.databinding.FragmentConverterBinding
import `in`.crazybytes.currencyconverter.main.MainViewModel
import `in`.crazybytes.currencyconverter.other.Constants.SOURCE_FROM
import `in`.crazybytes.currencyconverter.other.Constants.SOURCE_TO
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ConverterFragment : Fragment(), View.OnClickListener{

    private var _binding: FragmentConverterBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
        viewModel.convert()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentConverterBinding.inflate(inflater, container, false)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fromCurrencyTitleTv.setOnClickListener(this)
        binding.toCurrencyTitleTv.setOnClickListener (this)
        binding.fromCurrencyAmountTv.setOnClickListener (this)
        binding.mcvBtnSwap.setOnClickListener (this)

        viewModel.fromCurrency.observe(viewLifecycleOwner) { currency ->

            binding.fromCurrencySymbolTv.text = currency.symbol
            binding.fromCurrencyTitleTv.text = currency.title
            binding.fromCurrencyCodeTv.text = currency.code

        }

        viewModel.toCurrency.observe(viewLifecycleOwner) { currency ->

            binding.toCurrencySymbolTv.text = currency.symbol
            binding.toCurrencyTitleTv.text = currency.title
            binding.toCurrencyCodeTv.text = currency.code

        }

        viewModel.amount.observe(viewLifecycleOwner) {
            binding.fromCurrencyAmountTv.text = it
        }

        viewModel.conversion.observe(viewLifecycleOwner) { currencyRateEvent ->
            when(currencyRateEvent) {
                is MainViewModel.CurrencyRateEvent.Success -> {
                    binding.toCurrencyAmountTv.text = String.format("%.2f", currencyRateEvent.result.rate)
//                    binding.toCurrencyTitleTv.text = currencyRateEvent.result.currency.title
//                    binding.toCurrencyCodeTv.text = currencyRateEvent.result.currency.code
//                    binding.toCurrencySymbolTv.text = currencyRateEvent.result.currency.symbol
                    binding.progressBar.isVisible = false
                }

                is MainViewModel.CurrencyRateEvent.Failure -> {
                    binding.progressBar.isVisible = false
                    Toast.makeText(context, currencyRateEvent.errorText, Toast.LENGTH_SHORT).show()
                }
                is MainViewModel.CurrencyRateEvent.Loading -> {
                    binding.progressBar.isVisible = true
                    binding.toCurrencyAmountTv.text = ""
                }

                is MainViewModel.CurrencyRateEvent.Empty -> {
                    binding.toCurrencyAmountTv.text = ""
                }
            }
        }

    }


    companion object {

        @JvmStatic
        fun newInstance() =
            ConverterFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    override fun onClick(v: View?) {
        v?.let {
            when(v.id) {
                R.id.fromCurrencyTitleTv -> {

                    findNavController().navigate(
                        ConverterFragmentDirections.actionConverterFragmentToSelectCurrencyFragment(SOURCE_FROM)
                    )
                }

                R.id.toCurrencyTitleTv -> {
                    findNavController().navigate(
                        ConverterFragmentDirections.actionConverterFragmentToSelectCurrencyFragment(SOURCE_TO)
                    )
                }

                R.id.fromCurrencyAmountTv -> {
                    findNavController().navigate(
                        ConverterFragmentDirections.actionConverterFragmentToAmountFragment(
                            binding.fromCurrencyAmountTv.text.toString()
                        )
                    )
                }

                R.id.mcvBtnSwap -> {
                    viewModel.swapCurrencies()
                }
            }
        }
    }
}