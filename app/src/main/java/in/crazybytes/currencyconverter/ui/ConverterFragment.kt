package `in`.crazybytes.currencyconverter.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import `in`.crazybytes.currencyconverter.databinding.FragmentConverterBinding
import `in`.crazybytes.currencyconverter.main.MainViewModel
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController


class ConverterFragment : Fragment() {

    private var _binding: FragmentConverterBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModels()

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
        _binding = FragmentConverterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fromCurrencyTitleTv.setOnClickListener {
            findNavController().navigate(
                ConverterFragmentDirections.actionConverterFragmentToSelectCurrencyFragment()
            )
        }

        binding.fromCurrencyAmountTv.setOnClickListener {
            findNavController().navigate(
                ConverterFragmentDirections.actionConverterFragmentToAmountFragment()
            )
        }



    }

    companion object {

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            ConverterFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}