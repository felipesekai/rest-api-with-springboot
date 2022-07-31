package br.com.sekai.controller

import br.com.sekai.Math.SimpleMath
import br.com.sekai.converter.NumberConverter.convertToDouble
import br.com.sekai.converter.NumberConverter.isNumeric
import br.com.sekai.exceptions.UnsupportedMAthOperationException
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.atomic.AtomicLong
import kotlin.math.sqrt

@RestController
class MathController {
    val counter : AtomicLong = AtomicLong()

    private val math = SimpleMath()

    @RequestMapping("/sum/{numberOne}/{numberTwo}")
    fun sum(
        @PathVariable(value="numberOne") numberOne: String?,
        @PathVariable(value="numberTwo") numberTwo: String?,
    ): Double {
        if(!isNumeric(numberOne) || !isNumeric(numberTwo))
            throw UnsupportedMAthOperationException("please set a numeric value!")
        return math.sum(convertToDouble(numberOne), convertToDouble(numberTwo))
//        if (numberOne != null && numberTwo != null) {
//            return numberOne + numberTwo
//        }
//        return 0.0
    }

    @RequestMapping("/sub/{numberOne}/{numberTwo}")
    fun sub(
        @PathVariable(value="numberOne") numberOne: String?,
        @PathVariable(value="numberTwo") numberTwo: String?,
    ): Double {
        if(!isNumeric(numberOne) || !isNumeric(numberTwo))
            throw UnsupportedMAthOperationException("please set a numeric value!")
        return math.sub(convertToDouble(numberOne),convertToDouble(numberTwo))

    }
    @RequestMapping("/div/{numberOne}/{numberTwo}")
    fun div(
        @PathVariable(value="numberOne") numberOne: String?,
        @PathVariable(value="numberTwo") numberTwo: String?,
    ): Double {
        if(!isNumeric(numberOne) || !isNumeric(numberTwo))
            throw UnsupportedMAthOperationException("please set a numeric value!")
        return math.div(convertToDouble(numberOne),convertToDouble(numberTwo))

    }
    @RequestMapping("/mult/{numberOne}/{numberTwo}")
    fun mult(
        @PathVariable(value="numberOne") numberOne: String?,
        @PathVariable(value="numberTwo") numberTwo: String?,
    ): Double {
        if(!isNumeric(numberOne) || !isNumeric(numberTwo))
            throw UnsupportedMAthOperationException("please set a numeric value!")
        return math.mult(convertToDouble(numberOne),convertToDouble(numberTwo))

    }


    @RequestMapping("/mean/{numberOne}/{numberTwo}")
    fun mean(
        @PathVariable(value="numberOne") numberOne: String?,
        @PathVariable(value="numberTwo") numberTwo: String?,
    ): Double {
        if(!isNumeric(numberOne) || !isNumeric(numberTwo))
            throw UnsupportedMAthOperationException("please set a numeric value!")
        return math.mean(convertToDouble(numberOne),convertToDouble(numberTwo))

    }
    @RequestMapping("/square/{number}")
    fun square(
        @PathVariable(value="number") number: String?,

        ): Double {
        if(!isNumeric(number))
            throw UnsupportedMAthOperationException("please set a numeric value!")
        return math.square(convertToDouble(number))

    }


}
