package br.com.sekai.Math

import org.springframework.web.bind.annotation.PathVariable
import kotlin.math.sqrt

class SimpleMath {
    fun sum( numberOne: Double,numberTwo: Double) = numberOne+numberTwo
    fun sub( numberOne: Double,numberTwo: Double) = numberOne-numberTwo
    fun mult( numberOne: Double,numberTwo: Double) = numberOne*numberTwo
    fun div( numberOne: Double,numberTwo: Double) = numberOne/numberTwo
    fun mean( numberOne: Double,numberTwo: Double) = (numberOne+numberTwo)/2
    fun square( numberOne: Double) = sqrt(numberOne)
}
