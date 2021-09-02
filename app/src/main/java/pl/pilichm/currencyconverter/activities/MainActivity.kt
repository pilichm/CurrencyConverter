package pl.pilichm.currencyconverter.activities

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import pl.pilichm.currencyconverter.R
import pl.pilichm.currencyconverter.utils.*
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.Exception
import java.net.URL


class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var fromCountry: CountryEnum = CountryEnum.NONE
    private var toCountry: CountryEnum = CountryEnum.NONE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpListeners()
        setUpActionBar()

//       getExchangeRatesData()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_activity_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.ic_refresh_exchange_rates_main -> {
                /* TODO Implement refresh in main activity. */
                Toast.makeText(applicationContext, "Refresh", Toast.LENGTH_SHORT).show()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    private fun setUpActionBar(){
        setSupportActionBar(toolbar_act_main)
    }

    private fun getExchangeRatesData(){
        for (currency in Constants.supportedCurrencies){
            GetExchangeRatesAsyncTask().execute(currency)
        }
    }

    private fun goToExchangeRatesForOneCurrency(currencyName: String){
        val intent = Intent(this, ExchangeRatesActivity::class.java)
        intent.putExtra(Constants.PASSED_CURRENCY, currencyName)
        startActivity(intent)
    }

    inner class GetExchangeRatesAsyncTask : AsyncTask<String, String, ArrayList<CurrencyRate>>() {

        override fun doInBackground(vararg currency: String?): ArrayList<CurrencyRate> {
            val exchangeRatesList = ArrayList<CurrencyRate>()

            try {
                val urlString= Constants.API_URL + Constants.API_KEY + Constants.API_SUFFIX + currency[0]
                val url = URL(urlString)
                val request = url.openConnection()
                request.connect()
                Log.i("doInBackground", "Connected")

                val gson = Gson()
                val inStreamReader = InputStreamReader(request.getInputStream() as InputStream)
                val responseData = gson.fromJson(inStreamReader, CurrencyRatesResponse::class.java)

                Log.i("responseData", "$request")

                if (responseData.result == "success"){
                    Log.i("RETURNED_value", "${responseData.conversion_rates.USD}")
                    exchangeRatesList.add(CurrencyRate("PLN", responseData.conversion_rates.PLN))
                    exchangeRatesList.add(CurrencyRate("USD", responseData.conversion_rates.USD))
                    exchangeRatesList.add(CurrencyRate("EUR", responseData.conversion_rates.EUR))
                    exchangeRatesList.add(CurrencyRate("JPY", responseData.conversion_rates.JPY))
                }

                Log.i("doInBackground", "$responseData")
            } catch (e: Exception){
                Log.e("doInBackground", e::class.java.name)
            }

            Log.i("RETURNED", "${exchangeRatesList.size}")
            return exchangeRatesList
        }
    }

    private fun setUpListeners(){
        cvPolandLeft.setOnClickListener(this)
        cvEULeft.setOnClickListener(this)
        cvUSALeft.setOnClickListener(this)
        cvJapanLeft.setOnClickListener(this)

        cvPolandRight.setOnClickListener(this)
        cvEURight.setOnClickListener(this)
        cvUSARight.setOnClickListener(this)
        cvJapanRight.setOnClickListener(this)

        buttonConvert.setOnClickListener(this)
    }

    private fun resetLeftButtonColors(){
        fromCountry = CountryEnum.NONE
        val defaultColor = ContextCompat.getColor(this, R.color.card_view_background)

        cvPolandLeft.setBackgroundColor(defaultColor)
        cvEULeft.setBackgroundColor(defaultColor)
        cvUSALeft.setBackgroundColor(defaultColor)
        cvJapanLeft.setBackgroundColor(defaultColor)
    }

    private fun resetRightButtonColors(){
        toCountry = CountryEnum.NONE
        val defaultColor = ContextCompat.getColor(this, R.color.card_view_background)

        cvPolandRight.setBackgroundColor(defaultColor)
        cvEURight.setBackgroundColor(defaultColor)
        cvUSARight.setBackgroundColor(defaultColor)
        cvJapanRight.setBackgroundColor(defaultColor)
    }

    override fun onClick(view: View?) {
        val selectedCol = ContextCompat.getColor(this, R.color.card_view_background_selected)
        when (view?.id) {
            /* From currencies. */
            R.id.cvPolandLeft -> {
                if (fromCountry==CountryEnum.POLAND)
                    goToExchangeRatesForOneCurrency("PLN")
                resetLeftButtonColors()
                fromCountry = CountryEnum.POLAND
                cvPolandLeft.setBackgroundColor(selectedCol)
            }
            R.id.cvEULeft -> {
                if (fromCountry==CountryEnum.EU)
                    goToExchangeRatesForOneCurrency("EUR")
                resetLeftButtonColors()
                fromCountry = CountryEnum.EU
                cvEULeft.setBackgroundColor(selectedCol)
            }
            R.id.cvUSALeft -> {
                if (fromCountry==CountryEnum.USA){
                    goToExchangeRatesForOneCurrency("USD")
                }
                resetLeftButtonColors()
                fromCountry = CountryEnum.USA
                cvUSALeft.setBackgroundColor(selectedCol)
            }
            R.id.cvJapanLeft -> {
                if (fromCountry==CountryEnum.JAPAN){
                    goToExchangeRatesForOneCurrency("JPY")
                }
                resetLeftButtonColors()
                fromCountry = CountryEnum.JAPAN
                cvJapanLeft.setBackgroundColor(selectedCol)
            }
            /* To currencies. */
            R.id.cvPolandRight -> {
                if (toCountry==CountryEnum.POLAND){
                    goToExchangeRatesForOneCurrency("PLN")
                }
                resetRightButtonColors()
                toCountry = CountryEnum.POLAND
                cvPolandRight.setBackgroundColor(selectedCol)
            }
            R.id.cvEURight -> {
                if (toCountry==CountryEnum.EU){
                    goToExchangeRatesForOneCurrency("EUR")
                }
                resetRightButtonColors()
                toCountry = CountryEnum.EU
                cvEURight.setBackgroundColor(selectedCol)
            }
            R.id.cvUSARight -> {
                if (toCountry==CountryEnum.USA){
                    goToExchangeRatesForOneCurrency("USD")
                }
                resetRightButtonColors()
                toCountry = CountryEnum.USA
                cvUSARight.setBackgroundColor(selectedCol)
            }
            R.id.cvJapanRight -> {
                if (toCountry==CountryEnum.JAPAN){
                    goToExchangeRatesForOneCurrency("JPY")
                }
                resetRightButtonColors()
                toCountry = CountryEnum.JAPAN
                cvJapanRight.setBackgroundColor(selectedCol)
            }
            /* Convert button. */
            R.id.buttonConvert -> {
                if (toCountry==CountryEnum.NONE||fromCountry==CountryEnum.NONE){
                    val message = resources.getString(R.string.empty_from_or_to_currency)
                    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                } else if (toCountry==fromCountry){
                    val message = resources.getString(R.string.from_and_to_currencies_are_the_same)
                    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                } else {
                    val amount = etAmount.text.toString().toDouble()
                    val calculatedAmount
                        = amount * Util.getMockFromToMultiplier(fromCountry, toCountry)
                    etAmount.setText("%.2f".format(calculatedAmount))
                }
            }
        }
    }

}