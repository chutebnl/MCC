package algoritms

import net.objecthunter.exp4j.Expression
import net.objecthunter.exp4j.ExpressionBuilder
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    println("Ingrese la funcion derivada:")
    val exp1 = scanner.nextLine()
    println("Ingrese el valor de x0:")
    val x0 = scanner.nextDouble()
    println("Ingrese el valor de y0:")
    val y0 = scanner.nextDouble()
    println("Ingrese el incremento de x:")
    val h = scanner.nextDouble()
    println("Ingresela cantidad de puntos:")
    val cantPuntos = scanner.nextInt()
    println("Ingresela cantidad de decimales:")
    val cantDecimales = scanner.nextInt()

    val derivada = ExpressionBuilder(exp1)
            .variables("x", "y")
            .build()

    calcularMetodoEuler(derivada, x0, y0, h, cantPuntos, cantDecimales)
}

fun calcularMetodoEuler(derivada: Expression, x0: Double, y0: Double, h: Double, cantPuntos: Int, cantDecimales: Int) {
    var xi = x0
    var yi = y0
    var yiAux: Double

    for (i in 0 until cantPuntos) {
        yiAux = yi + (h * D(derivada, xi, yi))
        yiAux = redondear(yiAux, cantDecimales)
        println("y${i + 1} = y$i + (${h} * D(x${i}, y${i})) = $yi + (${h} * D($xi, $yi)) = $yiAux")
        xi = redondear(xi + h, cantDecimales)
        yi = yiAux
    }
}

private fun D(funcion: Expression, x: Double, y: Double): Double {
    funcion.setVariable("x", x)
    funcion.setVariable("y", y)
    return funcion.evaluate()
}

private fun redondear(num: Double, decimales: Int): Double {
    val redondeado = BigDecimal(num)
            .setScale(decimales, RoundingMode.HALF_EVEN)
    return redondeado.toDouble()
}