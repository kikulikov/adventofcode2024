package org.example

import java.nio.file.Files
import java.nio.file.Paths

private fun process(lines: List<String>) {

    val rows = lines.size
    val cols = lines.first().length
    val array = Array(rows) { Array(cols) { '.' } }

    for (i in array.indices) {            // Loop over rows
        for (j in array[i].indices) {     // Loop over columns
            array[i][j] = lines.get(i).toCharArray().get(j)
        }
    }

    var result = 0;

    for (i in 1 until rows - 1) {           // Loop over rows
        for (j in 1 until cols - 1) {        // Loop over columns
            if (array[i][j] == 'A') {
                if (((array[i - 1][j - 1] == 'M' && array[i + 1][j + 1] == 'S')
                            || (array[i - 1][j - 1] == 'S' && array[i + 1][j + 1] == 'M'))
                    && ((array[i - 1][j + 1] == 'M' && array[i + 1][j - 1] == 'S')
                            || (array[i - 1][j + 1] == 'S' && array[i + 1][j - 1] == 'M'))
                ) {
                    result += 1
                }
            }
        }
    }

    println(result)
}

private val sample = """
        MMMSXXMASM
        MSAMXMSMSA
        AMXSXMAAMM
        MSAMASMSMX
        XMASAMXAMM
        XXAMMXXAMA
        SMSMSASXSS
        SAXAMASAAA
        MAMMMXMMMM
        MXMXAXMASX
    """.trimIndent()

fun main() {
    process(sample.lines())
    process(fromFile())
}

private fun fromFile(): List<String> {
    val fileName = "src/main/resources/day4.txt"
    return Files.readAllLines(Paths.get(fileName))
}


