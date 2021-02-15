package `in`.crazybytes.currencyconverter.ui

import `in`.crazybytes.currencyconverter.R
import `in`.crazybytes.currencyconverter.data.models.RateHistory
import `in`.crazybytes.currencyconverter.databinding.FragmentConverterBinding
import `in`.crazybytes.currencyconverter.main.MainViewModel
import `in`.crazybytes.currencyconverter.other.Constants.SOURCE_FROM
import `in`.crazybytes.currencyconverter.other.Constants.SOURCE_TO
import `in`.crazybytes.currencyconverter.ui.views.CustomChartMarkerView
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.*
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ConverterFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentConverterBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by activityViewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
        viewModel.convert()
        viewModel.fetchRatesHistory()
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
        binding.toCurrencyTitleTv.setOnClickListener(this)
        binding.fromCurrencyAmountTv.setOnClickListener(this)
        binding.fabBtnSwap.setOnClickListener(this)

        //info: Setting TextSwitcher
        setFactoryAndAnimToAllTextSwitchers(activity as AppCompatActivity)

        //info: Setting Chart Details
//        setupChart(activity as AppCompatActivity)


        //info: Observing [fromCurrency] from mainViewModel
        viewModel.fromCurrency.observe(viewLifecycleOwner) { currencyEvent ->

            currencyEvent.getContentIfNotHandled().let { currency ->

                if (currency != null) {
                    binding.fromCurrencySymbolTv.setText(currency.symbol)
                    binding.fromCurrencyTitleTv.setText(currency.title)
                    binding.fromCurrencyCodeTv.setText(currency.code)
                } else {
                    binding.fromCurrencySymbolTv.setCurrentText(currencyEvent.peekContent().symbol)
                    binding.fromCurrencyTitleTv.setCurrentText(currencyEvent.peekContent().title)
                    binding.fromCurrencyCodeTv.setCurrentText(currencyEvent.peekContent().code)
                }

            }


        }

        //info: observing  [toCurrency] from mainViewModel
        viewModel.toCurrency.observe(viewLifecycleOwner) { currencyEvent ->

            currencyEvent.getContentIfNotHandled().let { currency ->

                if (currency != null) {
                    binding.toCurrencySymbolTv.setText(currency.symbol)
                    binding.toCurrencyTitleTv.setText(currency.title)
                    binding.toCurrencyCodeTv.setText(currency.code)
                } else {
                    binding.toCurrencySymbolTv.setCurrentText(currencyEvent.peekContent().symbol)
                    binding.toCurrencyTitleTv.setCurrentText(currencyEvent.peekContent().title)
                    binding.toCurrencyCodeTv.setCurrentText(currencyEvent.peekContent().code)
                }

            }

        }

        //info: observing the amount that is to be converted
        viewModel.amount.observe(viewLifecycleOwner) { amountEvent ->

            amountEvent.getContentIfNotHandled().let {
                if (it != null) {
                    binding.fromCurrencyAmountTv.text = it
                } else {
                    binding.fromCurrencyAmountTv.text = amountEvent.peekContent()
                }
            }

        }

        //info: observing the conversion rate
        viewModel.conversion.observe(viewLifecycleOwner) { currencyRateEvent ->

            when (currencyRateEvent) {
                is MainViewModel.CurrencyRateEvent.Success -> {
                    Log.d(TAG, "onViewCreated: ConvertedValue:- {${currencyRateEvent.result}}")
                    binding.toCurrencyAmountTv.text = currencyRateEvent.result
                    binding.progressBar.isVisible = false
                }

                is MainViewModel.CurrencyRateEvent.Failure -> {
                    binding.progressBar.isVisible = false
                    Toast.makeText(context, currencyRateEvent.errorText, Toast.LENGTH_SHORT)
                        .show()
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

        //info: observer for rateHistoryLiveData

        viewModel.rateHistoryLiveData.observe(viewLifecycleOwner) { rateHistoryEvent ->

            when (rateHistoryEvent) {

                is MainViewModel.RateHistoryEvent.Loading -> {
                    binding.chartProgressBar.isVisible = true
                }

                is MainViewModel.RateHistoryEvent.Failure -> {
                    binding.chartProgressBar.isVisible = false
                }

                is MainViewModel.RateHistoryEvent.Success -> {

                    setupChart(activity as AppCompatActivity, rateHistoryEvent.result)

                    binding.chartProgressBar.isVisible = false
                }

                is MainViewModel.RateHistoryEvent.Empty -> {
                    binding.chartProgressBar.isVisible = false
                }

            }
        }

    }

    private fun setupChart(appCompatActivity: AppCompatActivity, rateHistory: RateHistory) {


        val dataSet = LineDataSet(rateHistory.entries, "Rate")

        with(dataSet) {
            setDrawValues(false)
            setDrawFilled(true)
            fillDrawable = getDrawable(appCompatActivity, R.drawable.bg_fade_gradient)
            lineWidth = 4f
            color = getColor(appCompatActivity, R.color.purple_500)
            mode = LineDataSet.Mode.CUBIC_BEZIER
            setDrawCircleHole(true)
            circleRadius = 6f
            circleHoleRadius = 3f
            setCircleColor(getColor(appCompatActivity, R.color.purple_500))
            valueTextSize = 24f
        }



        with(binding.lineChartView) {

            //info: Setting The Data for The line chart here
            data = LineData(dataSet)

            xAxis.labelRotationAngle = 0f
            xAxis.setCenterAxisLabels(false)
            xAxis.granularity = 1f
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            xAxis.setDrawGridLines(false)
            xAxis.valueFormatter = object : ValueFormatter() {
                override fun getFormattedValue(value: Float): String {
                    return rateHistory.labels[value.toInt()]
                }

            }

            axisLeft.setDrawGridLines(false)
            axisRight.setDrawGridLines(false)

            axisRight.isEnabled = false
            axisLeft.isEnabled = false
            description.text = ""
            setNoDataText("No Data available!")
            legend.isEnabled = false

            //animate function can also be used to animate the chart here in place of invalidate()

            invalidate()
        }


        //info: CustomMarkerView Code
        val markerView = CustomChartMarkerView(appCompatActivity, rateHistory.toCurrency.symbol)
        binding.lineChartView.marker = markerView
    }

    private fun setFactoryAndAnimToAllTextSwitchers(activity: AppCompatActivity) {

        val textEnterUp = AnimationUtils.loadAnimation(activity, R.anim.text_enter_up)
        val textEnterDown = AnimationUtils.loadAnimation(activity, R.anim.text_enter_down)
        val textExitUp = AnimationUtils.loadAnimation(activity, R.anim.text_exit_up)
        val textExitDown = AnimationUtils.loadAnimation(activity, R.anim.text_exit_down)

        binding.fromCurrencyTitleTv.setFactory {
            val textView = TextView(activity)
            textView.gravity = Gravity.END
            textView.textSize = 16f
            textView.textAlignment = View.TEXT_ALIGNMENT_TEXT_END
            textView.setTextColor(Color.WHITE)
            textView.setCompoundDrawablesWithIntrinsicBounds(
                0,
                0,
                R.drawable.ic_arrow_drop_down_white,
                0
            )
            textView.compoundDrawablePadding = 8
            textView
        }

        binding.fromCurrencyTitleTv.inAnimation = textEnterDown
        binding.fromCurrencyTitleTv.outAnimation = textExitUp

        binding.fromCurrencyCodeTv.setFactory {
            val textView = TextView(activity)
            textView.gravity = Gravity.TOP or Gravity.CENTER_HORIZONTAL
            textView.textSize = 20f
            textView.setTextColor(getColor(activity, R.color.transparentWhite))
            textView
        }

        binding.fromCurrencyCodeTv.inAnimation = textEnterDown
        binding.fromCurrencyCodeTv.outAnimation = textExitUp

        binding.fromCurrencySymbolTv.setFactory {
            val textView = TextView(activity)
            textView.gravity = Gravity.TOP or Gravity.CENTER_HORIZONTAL
            textView.textSize = 20f
            textView.setTextColor(getColor(activity, R.color.transparentWhite))
            textView
        }

        binding.fromCurrencySymbolTv.inAnimation = textEnterDown
        binding.fromCurrencySymbolTv.outAnimation = textExitUp

        binding.toCurrencyTitleTv.setFactory {
            val textView = TextView(activity)
            textView.gravity = Gravity.END
            textView.textSize = 16f
            textView.textAlignment = View.TEXT_ALIGNMENT_TEXT_END
            textView.setTextColor(getColor(activity, R.color.purple_500))
            textView.setCompoundDrawablesWithIntrinsicBounds(
                0,
                0,
                R.drawable.ic_arrow_drop_down_purple,
                0
            )
            textView.compoundDrawablePadding = 8
            textView
        }

        binding.toCurrencyTitleTv.inAnimation = textEnterUp
        binding.toCurrencyTitleTv.outAnimation = textExitDown

        binding.toCurrencyCodeTv.setFactory {
            val textView = TextView(activity)
            textView.gravity = Gravity.TOP or Gravity.CENTER_HORIZONTAL
            textView.textSize = 20f
            textView.setTextColor(getColor(activity, R.color.transparentPurple))
            textView
        }

        binding.toCurrencyCodeTv.inAnimation = textEnterUp
        binding.toCurrencyCodeTv.outAnimation = textExitDown

        binding.toCurrencySymbolTv.setFactory {
            val textView = TextView(activity)
            textView.gravity = Gravity.TOP or Gravity.CENTER_HORIZONTAL
            textView.textSize = 20f
            textView.setTextColor(getColor(activity, R.color.transparentPurple))
            textView
        }

        binding.toCurrencySymbolTv.inAnimation = textEnterUp
        binding.toCurrencySymbolTv.outAnimation = textExitDown

    }


    companion object {

        private const val TAG = "ConverterFragment"

        @JvmStatic
        fun newInstance() =
            ConverterFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    override fun onClick(v: View?) {
        v?.let {
            when (v.id) {
                R.id.fromCurrencyTitleTv -> {

                    findNavController().navigate(
                        ConverterFragmentDirections.actionConverterFragmentToSelectCurrencyFragment(
                            SOURCE_FROM
                        )
                    )
                }

                R.id.toCurrencyTitleTv -> {
                    findNavController().navigate(
                        ConverterFragmentDirections.actionConverterFragmentToSelectCurrencyFragment(
                            SOURCE_TO
                        )
                    )
                }

                R.id.fromCurrencyAmountTv -> {
                    findNavController().navigate(
                        ConverterFragmentDirections.actionConverterFragmentToAmountFragment(
                            binding.fromCurrencyAmountTv.text.toString()
                        )
                    )
                }

                R.id.fabBtnSwap -> {
                    viewModel.swapCurrencies()
                }
            }
        }
    }
}