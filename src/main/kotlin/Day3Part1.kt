package org.example

import java.nio.file.Files
import java.nio.file.Paths

private fun process(line: String) {

    val rgx = "(mul\\([0-9]{1,3},[0-9]{1,3}\\))".toRegex()
    val rgxMul = "mul\\(([0-9]+),([0-9]+)\\)".toRegex()

    val results = rgx.findAll(line).toList().flatMap { match ->
        rgxMul.findAll(match.value).toList().map { p -> p.groupValues[1].toInt() * p.groupValues[2].toInt()}
    }.sum()

    println(results)
}

private val sample = """
        xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))
    """.trimIndent()

fun main() {
    process(sample)
    process(fromFile())
}

private fun fromFile(): String {
    val fileName = "src/main/resources/day3.txt"
    return Files.readString(Paths.get(fileName))
}

