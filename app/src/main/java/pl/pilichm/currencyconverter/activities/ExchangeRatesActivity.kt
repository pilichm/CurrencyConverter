package pl.pilichm.currencyconverter.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_exchange_rates_acivity.*
import pl.pilichm.currencyconverter.R
import pl.pilichm.currencyconverter.utils.Constants

class ExchangeRatesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exchange_rates_acivity)

        /* TODO Set up exchange rates for passed currency. */
        if (intent.hasExtra(Constants.PASSED_CURRENCY)){
            val passedCurrency = intent.getStringExtra(Constants.PASSED_CURRENCY)
            Toast.makeText(applicationContext, "$passedCurrency", Toast.LENGTH_SHORT).show()
        }

        setUpActionBar()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.exchange_rates_manu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    /* TODO Change displayed exchange factor when user selects different country. */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.menu_poland -> {
                Toast.makeText(applicationContext, "Poland", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.menu_european_union -> {
                Toast.makeText(applicationContext, "EU", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.menu_united_states -> {
                Toast.makeText(applicationContext, "USA", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.menu_japan -> {
                Toast.makeText(applicationContext, "Japan", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.ic_refresh_exchange_rates_detail -> {
                /* TODO Implement refresh functionality in ExchangeRatesActivity. */
                Toast.makeText(applicationContext, "Refresh", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setUpActionBar(){
        setSupportActionBar(toolbar_exc_rates_act)
    }
}