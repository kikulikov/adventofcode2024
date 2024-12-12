package org.example

import java.nio.file.Files
import java.nio.file.Paths

private fun process(lines: List<String>) {

    val rows = lines.size
    val cols = lines.first().length
    val array = Array(rows) { Array(cols) { 0 } }

    for (i in array.indices) {            // Loop over rows
        for (j in array[i].indices) {     // Loop over columns
            array[i][j] = lines.get(i).toCharArray().get(j).digitToInt()
        }
    }

//    array.forEach { row ->
//        row.forEach { print(it) }
//        println() // Move to next line after each row
//    }

    var totalCount = 0

    for (i in array.indices) {
        for (j in array[0].indices) {
            if (array[i][j] == 0) {
                var visited: MutableSet<Pair<Int, Int>> = mutableSetOf()
                find(array, i, j, visited)
                totalCount += visited.size
            }
        }
    }

    println(totalCount)
}

private fun find(array: Array<Array<Int>>, i: Int, j: Int, visited: MutableSet<Pair<Int, Int>>): Int {
    // println("$i:$j = ${array[i][j]}")

    if (array[i][j] == 9) {
        visited.add(Pair(i, j))
        return 1
    }

    val next = array[i][j] + 1
    var count = 0

    // Add a visited tracking mechanism
    val tracking = Array(array.size) { BooleanArray(array[0].size) }
    tracking[i][j] = true

    // Recursive calls with boundary and value checks
    if (i - 1 >= 0 && array[i - 1][j] == next && !tracking[i - 1][j]) {
        count += find(array, i - 1, j, visited)
    }

    if (i + 1 < array.size && array[i + 1][j] == next && !tracking[i + 1][j]) {
        count += find(array, i + 1, j, visited)
    }

    if (j - 1 >= 0 && array[i][j - 1] == next && !tracking[i][j - 1]) {
        count += find(array, i, j - 1, visited)
    }

    if (j + 1 < array[0].size && array[i][j + 1] == next && !tracking[i][j + 1]) {
        count += find(array, i, j + 1, visited)
    }

    return visited.size
}

private val sample = """
        89010123
        78121874
        87430965
        96549874
        45678903
        32019012
        01329801
        10456732
    """.trimIndent()

private val edge1 = """
    ...0...
    ...1...
    ...2...
    6543456
    7.....7
    8.....8
    9.....9
""".trimIndent().replace('.', '1')

private val edge2 = """
    ..90..9
    ...1.98
    ...2..7
    6543456
    765.987
    876....
    987....
""".trimIndent().replace('.', '1')

fun main() {
    process(edge1.lines())
    process(edge2.lines())
    process(sample.lines())
    process(fromFile())
}

private fun fromFile(): List<String> {
    val fileName = "src/main/resources/day10.txt"
    return Files.readAllLines(Paths.get(fileName))
}


