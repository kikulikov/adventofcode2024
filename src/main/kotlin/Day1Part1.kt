package org.example

import java.nio.file.Files
import java.nio.file.Paths
import kotlin.math.abs

private fun process(lines: List<String>) {
    val pairs = lines
        .map { line ->
            val parts = line.trim().split("\\s+".toRegex())
            Pair(parts[0].toInt(), parts[1].toInt())
        }

    val (left, right) = pairs.unzip()

    val result = left.sorted().zip(right.sorted()).sumOf { (m, n) -> abs(m - n) }
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
