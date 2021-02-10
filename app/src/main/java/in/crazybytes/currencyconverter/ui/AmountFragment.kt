package `in`.crazybytes.currencyconverter.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import `in`.crazybytes.currencyconverter.R
import `in`.crazybytes.currencyconverter.databinding.FragmentAmountBinding
import androidx.navigation.fragment.findNavController


/**
 * A simple [Fragment] subclass.
 * Use the [AmountFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AmountFragment : Fragment() {

    private var _binding: FragmentAmountBinding? = null
    val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAmountBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.doneBtn.isEnabled = false

        binding.btnCloseIv.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            AmountFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}