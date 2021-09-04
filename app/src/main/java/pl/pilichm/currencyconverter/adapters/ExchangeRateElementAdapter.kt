package pl.pilichm.currencyconverter.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.currency_exchenge_item.view.*
import pl.pilichm.currencyconverter.R
import pl.pilichm.currencyconverter.utils.CurrencyRate

class ExchangeRateElementAdapter(context: Context, elements: ArrayList<CurrencyRate>):
    ArrayAdapter<CurrencyRate>(context, 0, elements) {
    private var mBaseCurrency: String = "None"

    fun setBaseCurrency(bCurrency: String){
        mBaseCurrency = bCurrency
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val element = getItem(position)
        Log.i("Element", "${element?.exchangeRate}")
        val nView = convertView
            ?: LayoutInflater.from(context)
                .inflate(R.layout.currency_exchenge_item, parent, false)

        nView.elementExchangeRate?.text = element?.exchangeRate.toString()
        nView.elementToCurrency?.text = "${element?.name.toString()} = "
        Log.i("Element", "ADDED")

        return nView
    }
}