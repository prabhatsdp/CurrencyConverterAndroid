package `in`.crazybytes.currencyconverter.main

import `in`.crazybytes.currencyconverter.R
import `in`.crazybytes.currencyconverter.databinding.ActivityMainBinding
import `in`.crazybytes.currencyconverter.main.MainViewModel
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
////
//        setSupportActionBar(binding.toolbar)
//        val navController = findNavController(R.id.fragment)
//        setupActionBarWithNavController(navController)

//        supportActionBar?.let {
//            if(it.isShowing) {
//                it.hide()
//            }
//        }
//
//        navController.addOnDestinationChangedListener { nc, navDestination, b ->
//            when (navDestination.id) {
//                R.id.converterFragment -> {
//                    Log.d(TAG, "onCreate: ConverterFragment Destination===============")
//                    supportActionBar?.let {
//                        Log.d(TAG, "onCreate: ActionBar is not null ---------------------")
//                        if (it.isShowing) {
//                            it.hide()
//                        }
//                    }
//
////                    hideActionBar(binding.appbarLayout)
//                }
//                R.id.amountFragment -> {
//
//                    Log.d(TAG, "onCreate: AmountFragment Destination===============")
//                    supportActionBar?.let {
//                        if(it.isShowing) {
//                            it.hide()
//                        }
//                    }
//
//
////                    hideActionBar(binding.appbarLayout)
//                }
//                R.id.selectCurrencyFragment -> {
//
//                    Log.d(TAG, "onCreate: SelectCurrencyFragment Destination===============")
//                    supportActionBar?.let {
//                        if(!it.isShowing) {
//                            it.show()
//                        }
//                    }
//
////                    showActionBar(binding.appbarLayout)
//                }
//                else -> {
//
//                    Log.d(TAG, "onCreate: Nothing Destination===============")
//                    supportActionBar?.let {
//                        if(!it.isShowing) {
//                            it.show()
//                        }
//                    }
//
////                    hideActionBar(binding.appbarLayout)
//                }
//            }
//        }


    }

    fun hideActionBar(actionBarView: View) {
        with(actionBarView) {
            if (visibility == View.VISIBLE && alpha == 1f) {
                animate()
                    .translationY(-200.0f)
                    .withEndAction { visibility = View.GONE }
                    .duration = 300
            }
        }
    }

    fun showActionBar(actionBarView: View) {
        with(actionBarView) {
            visibility = View.VISIBLE
            animate()
                .translationY(0f)
                .alpha(1f)
                .duration = 300
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }

}
