package org.example

import java.nio.file.Files
import java.nio.file.Paths

private fun process(lines: List<String>) {
    val pairs = lines
        .map { line ->
            val parts = line.trim().split("\\s+".toRegex()).map { n -> n.toInt() }
            var safe = false

            for (j in 0 until parts.size) {

                val updated = parts.filterIndexed { index, _ -> index != j }
                var dec = true;
                var inc = true;

                for (i in 1 until updated.size) {
                    if (!(dec && updated[i - 1] > updated[i] && updated[i - 1] - updated[i] <= 3)) {
                        dec = false;
                    }
                }

                for (i in 1 until updated.size) {
                    if (!(inc && updated[i - 1] < updated[i] && updated[i] - updated[i - 1] <= 3)) {
                        inc = false;
                    }
                }

                if (dec || inc) {
                    safe = true
                }
            }

            safe;
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

