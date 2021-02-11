package `in`.crazybytes.currencyconverter.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import `in`.crazybytes.currencyconverter.R
import `in`.crazybytes.currencyconverter.databinding.FragmentAmountBinding
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs


/**
 * A simple [Fragment] subclass.
 * Use the [AmountFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AmountFragment : Fragment(), View.OnClickListener {

    private val args: AmountFragmentArgs by navArgs()

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
    ): View {
        _binding = FragmentAmountBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.doneBtn.isEnabled = false

        binding.numberOne.setOnClickListener(this)
        binding.numberTwo.setOnClickListener(this)
        binding.numberThree.setOnClickListener(this)
        binding.numberFour.setOnClickListener(this)
        binding.numberFive.setOnClickListener(this)
        binding.numberSix.setOnClickListener(this)
        binding.numberSeven.setOnClickListener(this)
        binding.numberEight.setOnClickListener(this)
        binding.numberZero.setOnClickListener(this)
        binding.decimalPoint.setOnClickListener(this)
        binding.doneBtn.setOnClickListener(this)
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            AmountFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    override fun onClick(v: View?) {
        v?.let {
            when(v.id) {
                R.id.numberOne -> {

                }
                R.id.numberTwo -> {

                }
                R.id.numberThree -> {

                }
                R.id.numberFour -> {

                }
                R.id.numberFive -> {

                }
                R.id.numberSix -> {

                }
                R.id.numberSeven -> {

                }
                R.id.numberEight -> {

                }
                R.id.numberNine -> {

                }
                R.id.numberZero -> {

                }
                R.id.decimalPoint -> {

                }
                R.id.doneBtn -> {

                }

                R.id.btnCloseIv -> {
                    findNavController().navigateUp()
                }
                R.id.tapToDeleteTv -> {

                }
                else -> {}

            }
        }
    }
}