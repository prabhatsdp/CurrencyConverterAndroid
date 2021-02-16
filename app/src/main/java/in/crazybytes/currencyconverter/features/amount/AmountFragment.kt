package `in`.crazybytes.currencyconverter.features.amount

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import `in`.crazybytes.currencyconverter.R
import `in`.crazybytes.currencyconverter.databinding.FragmentAmountBinding
import `in`.crazybytes.currencyconverter.features.main.MainViewModel
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint


/**
 * A simple [Fragment] subclass.
 * Use the [AmountFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

@AndroidEntryPoint
class AmountFragment : Fragment(), View.OnClickListener {

    private val args: AmountFragmentArgs by navArgs()
    private val enterAmountViewModel: EnterAmountViewModel by viewModels()
    private val mainViewModel: MainViewModel by activityViewModels()

    private var _binding: FragmentAmountBinding? = null
    val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }

        enterAmountViewModel.setEnteredAmount(args.amount)
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
        binding.numberNine.setOnClickListener(this)
        binding.numberZero.setOnClickListener(this)
        binding.decimalPoint.setOnClickListener(this)
        binding.doneBtn.setOnClickListener(this)
        binding.tapToDeleteTv.setOnClickListener(this)
        binding.btnCloseIv.setOnClickListener(this)


        enterAmountViewModel.enteredAmount.observe(viewLifecycleOwner) {
            binding.amountTv.text = it
            binding.doneBtn.isEnabled = !(it.equals(args.amount) || it.equals("0"))
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

    override fun onClick(v: View?) {
        v?.let {
            when(v.id) {
                R.id.numberOne -> {
                    enterAmountViewModel.appendNumber("1")
                }
                R.id.numberTwo -> {
                    enterAmountViewModel.appendNumber("2")
                }
                R.id.numberThree -> {
                    enterAmountViewModel.appendNumber("3")
                }
                R.id.numberFour -> {
                    enterAmountViewModel.appendNumber("4")
                }
                R.id.numberFive -> {
                    enterAmountViewModel.appendNumber("5")
                }
                R.id.numberSix -> {
                    enterAmountViewModel.appendNumber("6")
                }
                R.id.numberSeven -> {
                    enterAmountViewModel.appendNumber("7")
                }
                R.id.numberEight -> {
                    enterAmountViewModel.appendNumber("8")
                }
                R.id.numberNine -> {
                    enterAmountViewModel.appendNumber("9")
                }
                R.id.numberZero -> {
                    enterAmountViewModel.appendNumber("0")
                }
                R.id.decimalPoint -> {
                    enterAmountViewModel.appendDecimal()
                }
                R.id.doneBtn -> {
                    mainViewModel.setAmount(binding.amountTv.text.toString())
                    findNavController().navigateUp()
                }

                R.id.btnCloseIv -> {
                    findNavController().navigateUp()
                }
                R.id.tapToDeleteTv -> {
                    enterAmountViewModel.deleteLastCharacter()
                }
                else -> {}

            }
        }
    }
}