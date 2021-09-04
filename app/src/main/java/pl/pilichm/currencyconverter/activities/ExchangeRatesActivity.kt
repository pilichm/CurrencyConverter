package pl.pilichm.currencyconverter.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_exchange_rates_acivity.*
import pl.pilichm.currencyconverter.R
import pl.pilichm.currencyconverter.adapters.ExchangeRateElementAdapter
import pl.pilichm.currencyconverter.utils.Constants
import pl.pilichm.currencyconverter.utils.CountryEnum
import pl.pilichm.currencyconverter.utils.CurrencyRate
import pl.pilichm.currencyconverter.utils.Util

class ExchangeRatesActivity : AppCompatActivity() {
    private var mListViewAdapter: ExchangeRateElementAdapter? = null
    private var mElements: ArrayList<CurrencyRate>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exchange_rates_acivity)

        if (intent.hasExtra(Constants.PASSED_CURRENCY)) {
            val passedCurrency = intent.getStringExtra(Constants.PASSED_CURRENCY)

            Log.i("passedCurrency", "$passedCurrency")

            tvExchangeListHeader.text = "Exchange rates for currency $passedCurrency:"
            mElements = getCurrenciesListForCountry(passedCurrency!!)
            mListViewAdapter = ExchangeRateElementAdapter(this, mElements!!)
            mListViewAdapter!!.setBaseCurrency(passedCurrency)
            listViewExchangeRates.adapter = mListViewAdapter
        }

        setUpActionBar()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.exchange_rates_manu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    /* TODO Change displayed exchange factor when user selects different country. */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var currencyName = "None"

        when (item.itemId){
            R.id.menu_poland -> {
                currencyName = "PLN"
                mElements = getCurrenciesListForCountry(currencyName)

            }
            R.id.menu_european_union -> {
                currencyName = "EUR"
                mElements = getCurrenciesListForCountry(currencyName)
            }
            R.id.menu_united_states -> {
                currencyName = "USD"
                mElements = getCurrenciesListForCountry(currencyName)
            }
            R.id.menu_japan -> {
                currencyName = "JPY"
                mElements = getCurrenciesListForCountry(currencyName)
            }
            R.id.ic_refresh_exchange_rates_detail -> {
                /* TODO Implement refresh functionality in ExchangeRatesActivity. */
                Toast.makeText(applicationContext, "Refresh", Toast.LENGTH_SHORT).show()
            }
            else -> return super.onOptionsItemSelected(item)
        }

        tvExchangeListHeader.text = "Exchange rates for currency $currencyName:"
        mListViewAdapter?.clear()
        mListViewAdapter?.addAll(mElements!!)
        mListViewAdapter?.notifyDataSetChanged()
        return true
    }

    private fun setUpActionBar(){
        setSupportActionBar(toolbar_exc_rates_act)
    }

    private fun getCurrenciesListForCountry(country: String): ArrayList<CurrencyRate>{
        val elements = ArrayList<CurrencyRate>()
        for (countryElement in CountryEnum.values()) {
            if (countryElement != CountryEnum.NONE) {
                elements.add(
                    CurrencyRate(
                        Util.getNameFromCountryEnum(countryElement),
                        Util.getMockFromToMultiplier(
                            Util.getEnumFromCurrency(country),
                            countryElement
                        )
                    )
                )
            }
        }
        return elements
    }
}