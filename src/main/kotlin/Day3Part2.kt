package org.example

import java.nio.file.Files
import java.nio.file.Paths

private fun process(line: String) {

    val rgx = "(mul\\([0-9]{1,3},[0-9]{1,3}\\))|(don't\\(\\))|(do\\(\\))".toRegex()
    val rgxMul = "mul\\(([0-9]+),([0-9]+)\\)".toRegex()
    val rgxDont = "don't\\(\\)".toRegex()
    val rgxDo = "do\\(\\)".toRegex()

    val finds = rgx.findAll(line).toList().map { m -> m.value }
    var result = 0
    var include = true

    for (f in finds) {
        if (rgxDont.matches(f)) {
            include = false;
            continue
        } else if (rgxDo.matches(f)) {
            include = true;
            continue
        }

        if (include){
            result += rgxMul.findAll(f).toList().map { p -> p.groupValues[1].toInt() * p.groupValues[2].toInt()}.first()
        }
    }

    println(result)
}

private val sample = """
        xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))
    """.trimIndent()

private fun fromFile(): String {
    val fileName = "src/main/resources/day3.txt"
    return Files.readString(Paths.get(fileName))
}

fun main() {
    process(sample)
    process(fromFile())
}