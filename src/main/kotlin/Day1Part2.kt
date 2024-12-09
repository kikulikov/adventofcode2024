package org.example

import java.nio.file.Files
import java.nio.file.Paths

private fun process(lines: List<String>) {
    val pairs = lines
        .map { line ->
            val parts = line.trim().split("\\s+".toRegex()) // Split by whitespace
            Pair(parts[0].toLong(), parts[1].toLong()) // Convert to a Pair of Integers
        }

    val (left, right) = pairs.unzip()
    val map = HashMap<Long, Long>()

    for (n in right) {
        map[n] = map.getOrDefault(n, 0) + 1
    }

    val result = left.map { n -> map.getOrDefault(n, 0) * n }.sum()
    println(result)
}

private val sample = """
        3   4
        4   3
        2   5
        1   3
        3   9
        3   3
    """.trimIndent()

private fun fromFile(): List<String> {
    val fileName = "src/main/resources/day1.txt"
    return Files.readAllLines(Paths.get(fileName))
}

fun main() {
    process(sample.lines())
    process(fromFile())
}

