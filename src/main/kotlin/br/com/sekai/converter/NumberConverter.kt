package br.com.sekai.converter

object NumberConverter {

    fun convertToDouble(number: String?): Double {
        if (number.isNullOrBlank()) return 0.0
        val _number = number.replace(",".toRegex(), ".")

        return if(isNumeric(number)) number.toDouble() else 0.0

    }

    fun isNumeric(number: String?): Boolean {
        if (number.isNullOrBlank()) return false
        val _number = number.replace(",".toRegex(), ".")
        return _number.matches("""[-+]?[0-9]*\.?[0-9]+""".toRegex())
    }
}
