package org.example

import java.nio.file.Files
import java.nio.file.Paths

// TODO figure out
private fun generatePermutationsRecursive(n: Int): List<List<String>> {
    return when (n) {
        0 -> listOf(emptyList())
        else -> generatePermutationsRecursive(n - 1).flatMap {
            listOf(it + "*", it + "+", it + "|")
        }
    }
}

private fun process(lines: List<String>) {
    val results = lines.map { l ->
        val parts = l.split(":")
        Pair(parts[0].toLong(), parts[1].trim().split(" ").map { s -> s.toLong() })
    }.filter { pair ->
        val sum = pair.first
        val numbers = pair.second
        val operators = generatePermutationsRecursive(numbers.size - 1)
        var found = false

        for (op in operators) {
            var agg = numbers[0];

            for (i in 1 until numbers.size) {
                if (op[i - 1] == "+") {
                    agg += numbers[i]
                } else if (op[i - 1] == "*") {
                    agg *= numbers[i]
                } else {
                    agg = (agg.toString() + numbers[i].toString()).toLong()
                }
            }

            if (agg == sum) {
                found = true
                break
            }
        }

        found
    }

    println(results.sumOf { p -> p.first })
}

private val sample = """
        190: 10 19
        3267: 81 40 27
        83: 17 5
        156: 15 6
        7290: 6 8 6 15
        161011: 16 10 13
        192: 17 8 14
        21037: 9 7 18 13
        292: 11 6 16 20
    """.trimIndent()

private fun fromFile(): List<String> {
    val fileName = "src/main/resources/day7.txt"
    return Files.readAllLines(Paths.get(fileName))
}

fun main() {
    println(generatePermutationsRecursive(1))
    process(sample.lines())
    process(fromFile())
}
