package pl.pilichm.currencyconverter.utils

data class CurrencyRatesResponse(
    val result: String,
    val base_code: String,
    val conversion_rates: ConversionRates
)

data class ConversionRates(
    val PLN: Double,
    val EUR: Double,
    val USD: Double,
    val JPY: Double
)
