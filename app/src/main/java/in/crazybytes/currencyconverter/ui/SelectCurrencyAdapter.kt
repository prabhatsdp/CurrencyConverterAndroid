package `in`.crazybytes.currencyconverter.ui

import `in`.crazybytes.currencyconverter.data.models.Currency
import `in`.crazybytes.currencyconverter.databinding.ItemSelectCurrencyBinding
import `in`.crazybytes.currencyconverter.other.Helper.currencyList
import `in`.crazybytes.currencyconverter.utils.CurrencySelectionListener
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * Created By Prabhat Pandey for CrazyBytes
 * on Monday, 08 February, 2021 at 7:52 AM
 */

class SelectCurrencyAdapter(
    private val currencySelectionListener: CurrencySelectionListener
) : RecyclerView.Adapter<SelectCurrencyAdapter.CurrencyViewHolder>() {

    class CurrencyViewHolder(
        private val _itemBinding: ItemSelectCurrencyBinding
    ) : RecyclerView.ViewHolder(_itemBinding.root) {

        fun bind(currency: Currency) {
            with(_itemBinding) {
                currencyTitleTv.text = currency.title
                currencyCodeTv.text = currency.code
                currencySymbolTv.text = currency.symbol
                root.setOnClickListener {
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {

        val itemBinding = ItemSelectCurrencyBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return CurrencyViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.bind(currencyList[position])
        holder.itemView.setOnClickListener {
            currencySelectionListener.onCurrencySelected(currencyList[position])
        }
    }

    override fun getItemCount(): Int {
        return currencyList.size
    }
}