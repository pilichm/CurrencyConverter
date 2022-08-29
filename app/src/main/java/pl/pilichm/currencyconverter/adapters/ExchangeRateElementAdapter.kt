package pl.pilichm.currencyconverter.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
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

        val exchangeRate = nView.findViewById(R.id.elementExchangeRate) as TextView
        val elementToCurrency= nView.findViewById(R.id.elementToCurrency) as TextView

        exchangeRate.text = element?.exchangeRate.toString()
        elementToCurrency.text = "${element?.name.toString()} = "
        Log.i("Element", "ADDED")

        return nView
    }
}