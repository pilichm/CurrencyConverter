package pl.pilichm.currencyconverter.utils

class Util {
    companion object {
        fun getMockFromToMultiplier(fromCurr: CountryEnum, toCurr: CountryEnum): Double {
            when (fromCurr) {
                CountryEnum.EU -> {
                    return when (toCurr) {
                        CountryEnum.POLAND -> 4.5503
                        CountryEnum.USA -> 1.18
                        CountryEnum.JAPAN -> 129.64
                        else -> 0.0
                    }
                }
                CountryEnum.POLAND -> {
                    return when (toCurr) {
                        CountryEnum.EU -> 0.22
                        CountryEnum.USA -> 0.26
                        CountryEnum.JAPAN -> 28.42
                        else -> 0.0
                    }
                }
                CountryEnum.USA -> {
                    return when (toCurr) {
                        CountryEnum.POLAND -> 3.87
                        CountryEnum.EU -> 0.85
                        CountryEnum.JAPAN -> 109.91
                        else -> 0.0
                    }
                }
                CountryEnum.JAPAN -> {
                    return when (toCurr) {
                        CountryEnum.POLAND -> 0.04
                        CountryEnum.EU -> 0.0077
                        CountryEnum.USA -> 0.0091
                        else -> 0.0
                    }
                }
                else -> return 0.0
            }
        }
    }
}