package org.example

import java.nio.file.Files
import java.nio.file.Paths

private fun check(m: Char, a: Char, s: Char): Boolean {
    return m == 'M' && a == 'A' && s == 'S'
}

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

    for (i in array.indices) {            // Loop over rows
        for (j in array[i].indices) {     // Loop over columns
            if (array[i][j] == 'X') {
                // right
                if (j <= cols - 4 && array[i][j + 1] == 'M' && array[i][j + 2] == 'A' && array[i][j + 3] == 'S') {
                    result+=1;
                }
                // left
                if (j >= 3 && array[i][j - 1] == 'M' && array[i][j - 2] == 'A' && array[i][j - 3] == 'S') {
                    result+=1;
                }
                // down
                if (i <= rows - 4 && array[i + 1][j] == 'M' && array[i + 2][j] == 'A' && array[i + 3][j] == 'S') {
                    result+=1;
                }
                // up
                if (i >= 3 && array[i - 1][j] == 'M' && array[i - 2][j] == 'A' && array[i - 3][j] == 'S') {
                    result+=1;
                }
                // down-right
                if (i <= rows - 4 && j <= cols - 4 && check(array[i+1][j + 1], array[i+2][j + 2], array[i+3][j + 3])) {
                    result+=1;
                }
                // down-left
                if (i <= rows - 4 && j >= 3 && check(array[i+1][j - 1], array[i+2][j - 2], array[i+3][j - 3])) {
                    result+=1;
                }
                // up-right
                if (i >= 3 && j <= cols - 4 && check(array[i-1][j + 1], array[i - 2][j + 2], array[i - 3][j + 3])) {
                    result+=1;
                }
                // up-left
                if (i > 2 && j > 2 && check(array[i - 1][j - 1], array[i - 2][j - 2], array[i - 3][j - 3])) {
                    result += 1;
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


