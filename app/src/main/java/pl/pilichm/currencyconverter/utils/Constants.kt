package pl.pilichm.currencyconverter.utils

class Constants {
    companion object {
        const val API_URL = "https://v6.exchangerate-api.com/v6/"
        const val API_SUFFIX = "/latest/"
        const val API_KEY = "-"
        const val PASSED_CURRENCY = "passed_currency"
//        val supportedCurrencies = listOf("PLN", "EUR", "USD", "JPY")
        val supportedCurrencies = listOf("PLN")
    }
}