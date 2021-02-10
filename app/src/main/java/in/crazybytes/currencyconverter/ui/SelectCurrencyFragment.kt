package `in`.crazybytes.currencyconverter.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import `in`.crazybytes.currencyconverter.R
import `in`.crazybytes.currencyconverter.data.models.Currency
import `in`.crazybytes.currencyconverter.databinding.FragmentSelectCurrencyBinding
import `in`.crazybytes.currencyconverter.other.Constants.SOURCE_FROM
import `in`.crazybytes.currencyconverter.other.Constants.SOURCE_TO
import `in`.crazybytes.currencyconverter.utils.CurrencySelectionListener
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.LinearLayoutManager

/**
 * A simple [Fragment] subclass.
 * Use the [SelectCurrencyFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SelectCurrencyFragment : Fragment() {

    private val args: SelectCurrencyFragmentArgs by navArgs()

    private var _binding: FragmentSelectCurrencyBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       _binding = FragmentSelectCurrencyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        when(args.source) {
            SOURCE_FROM -> {
                binding.toolbar.title = getString(R.string.select_from_currency)
            }

            SOURCE_TO -> {
                binding.toolbar.title = getString(R.string.select_to_currency)
            }

            else -> {
                binding.toolbar.title = getString(R.string.select_currency)
            }
        }

        val currencySelectionListener = object : CurrencySelectionListener {
            override fun onCurrencySelected(selectedCurrency: Currency) {
                Toast
                    .makeText(context, "${selectedCurrency.code} clicked", Toast.LENGTH_SHORT)
                    .show()
            }

        }

        with(binding.selectCurrencyRv) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = SelectCurrencyAdapter(currencySelectionListener)
        }

        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

    }

    companion object {

        private const val TAG = "SelectCurrencyFragment"

        @JvmStatic
        fun newInstance() =
            SelectCurrencyFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}