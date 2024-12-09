package org.example

import java.nio.file.Files
import java.nio.file.Paths

private fun process(lines: List<String>) {
    val pairs = lines
        .map { line ->
            val parts = line.trim().split("\\s+".toRegex()).map { n -> n.toInt() }

            var dec = true;

            for (i in 1 until parts.size) {
                if (!(dec && parts[i - 1] > parts[i] && parts[i - 1] - parts[i] <= 3)) {
                    dec = false;
                }
            }

            var inc = true;

            for (i in 1 until parts.size) {
                if (!(inc && parts[i - 1] < parts[i] && parts[i] - parts[i - 1] <= 3)) {
                    inc = false;
                }
            }

            dec || inc
        }

    val result = pairs.count { b -> b }
    println(result)
}

private val sample = """
        7 6 4 2 1
        1 2 7 8 9
        9 7 6 2 1
        1 3 2 4 5
        8 6 4 4 1
        1 3 6 7 9
    """.trimIndent()

private fun fromFile(): List<String> {
    val fileName = "src/main/resources/day2.txt"
    return Files.readAllLines(Paths.get(fileName))
}

fun main() {
    process(sample.lines())
    process(fromFile())
}
